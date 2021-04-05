package datastructure.segmentTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 方块下落的最大高度问题
 * 给定一个二位数组arr[][], arr[i][0]表示从第arr[i][0]个位置开始，下落一个arr[i][1]*arr[i][1]的正方形
 * 求取每下落一次方块，当前方块的最大高度是多少
 * 使用线段树解决
 */
public class FallingSquares {
    //以最大值构造线段树
    class SegmentTree {
        private int[] max; //最大高度的懒惰标记
        private boolean[] update; //更新懒惰标记
        private int[] change; //更新值


        public SegmentTree(int N) {
            int MAXN = N + 1;
            max = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
            change = new int[MAXN << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        /**
         * 任务下发: 之前的，所有懒更新，从父范围，发给左右两个子范围
         *
         * @param rt 当前位置
         * @param ln 线段树左子树的节点个数
         * @param rn 线段树右子树的节点个数
         */
        private void pushDown(int rt, int ln, int rn) {
            //先处理更新操作
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                max[rt << 1] = change[rt]; //清空之前的累加懒更新标记
                max[rt << 1 | 1] = change[rt];//清空之前的累加懒更新标记
                update[rt] = false; //清空父节点更新标记
            }
        }

        /**
         * taskLeft到taskRight范围上的元素更新尾target
         *
         * @param taskLeft  任务左边界
         * @param taskRight 任务右边界
         * @param target    更新目标值
         * @param l
         * @param r
         * @param rt
         */
        public void update(int taskLeft, int taskRight, int target, int l, int r, int rt) {
            //如果任务区间包含当前区间
            if (taskLeft <= l && taskRight >= r) {
                update[rt] = true;
                change[rt] = target;
                max[rt] = target; //需要将之前积攒的lazy清空
                return;
            }
            //如果任务区间不包含当前区间，此时无法懒更新
            int mid = (l + r) >> 1;
            //将之前的任务下发
            pushDown(rt, mid - l + 1, r - mid);

            if (taskLeft <= mid) {
                update(taskLeft, taskRight, target, l, mid, rt << 1);
            }

            if (taskRight > mid) {
                update(taskLeft, taskRight, target, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        /**
         * 查询taskLeft到taskRight的最大值
         *
         * @param taskLeft
         * @param taskRight
         * @param l
         * @param r
         * @param rt
         * @return
         */
        public int query(int taskLeft, int taskRight, int l, int r, int rt) {
            //如果任务包含，则直接返回对应的累加和即可
            if (taskLeft <= l && taskRight >= r) {
                return max[rt];
            }
            //如果任务不包含
            int mid = (l + r) >> 1;
            //首先下发积攒的任务
            pushDown(rt, mid - l + 1, r - mid);

            int left = 0;
            int right = 0;

            if (taskLeft <= mid) {
                left = query(taskLeft, taskRight, l, mid, rt << 1);
            }

            if (taskRight > mid) {
                right = query(taskLeft, taskRight, mid + 1, r, (rt << 1) | 1);
            }

            return Math.max(left, right);
        }

    }
    // positions
    // [2,7] -> 2 , 8
    // [3, 10] -> 3, 12
    //
    //
    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        // 100   -> 1    306 ->   2   403 -> 3
        // [100,403]   1~3
        int N = map.size(); // 1 ~ 	N
        //构造线段树，初始值max都是0
        SegmentTree segmentTree = new SegmentTree(N);
        int max = 0;
        List<Integer> res = new ArrayList<>();
        // 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[0] + arr[1] - 1);
            int height = segmentTree.query(L, R, 1, N, 1) + arr[1]; //当前区间的最大高度+目标高度
            max = Math.max(max, height); //更新最大值
            res.add(max); //添加入结果集
            segmentTree.update(L, R, height, 1, N, 1); //需要更新目标区间的最大高度为height
        }
        return res;
    }
}
