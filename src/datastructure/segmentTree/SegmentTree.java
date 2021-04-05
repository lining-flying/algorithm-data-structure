package datastructure.segmentTree;

/**
 * 线段树实现
 */
public class SegmentTree {
    private int[] arr ;
    private int MAXN ;
    private int[] sum ; //模拟线段树存储区间和
    private int[] lazy ; //累加的懒惰标记
    private boolean[] update ; //更新懒惰标记
    private int[] change ; //更新值


    public SegmentTree(int[] origin) {
        MAXN = origin.length + 1 ;
        arr = new int[MAXN] ;
        for(int i=0;i<origin.length;i++){
            arr[i+1] = origin[i];
        }

        sum = new int[MAXN<<2]; //4N的大小足够承载N的线段树元素了
        lazy = new int[MAXN<<2] ;
        update = new boolean[MAXN<<2];
        change = new int[MAXN<<2];

        build(1,MAXN,1); //调用build方法，初始化累加和
    }

    private void build(int L, int R, int rt) {
        if(L == R){
            sum[rt] = arr[L];
            return ;
        }
        int mid = (L + R) >> 1 ; //使用位运算提升计算速度
        build(L,mid,rt>>1);
        build(mid+1,R,(rt>>1)|1);
        pushUp(rt);
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt>>1] + sum[(rt>>1)|1] ;
    }

    /**
     * 任务下发: 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
     * @param rt 当前位置
     * @param ln 线段树左子树的节点个数
     * @param rn 线段树右子树的节点个数
     */
    private void pushDown(int rt, int ln, int rn){
        //先处理更新操作
        if(update[rt]){
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1] = 0; //清空之前的累加懒更新标记
            lazy[rt << 1 | 1] = 0;//清空之前的累加懒更新标记
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            update[rt] = false; //清空父节点更新标记
        }
        //再处理累加操作
        if(lazy[rt] != 0){
            lazy[rt<<1] += lazy[rt] ; //左孩子累加懒更新标记
            sum[rt<<1] += lazy[rt] * ln ;
            lazy[(rt<<1)|1] += lazy[rt]; //右孩子累加懒标记
            sum[(rt<<1)|1] += lazy[rt] * rn ;
            lazy[rt] = 0 ; //清空父节点累加懒更新标记
        }
    }

    /**
     * taskLeft到taskRight范围上所有的元素都增加C
     * @param taskLeft 真实的范围左边界
     * @param taskRight 真实的范围右边界
     * @param addTarget 要添加的目标值
     * @param l sum的区间左边界
     * @param r sum的区间右边界
     * @param rt 实际的下标值
     */
    public void add(int taskLeft,int taskRight,int addTarget, int l,int r,int rt){
        //如果任务包含了当前范围
        if(taskLeft<=l && taskRight>=r){
            lazy[rt] += addTarget;
            sum[rt] += (r-l+1)*addTarget;
            return ;
        }
        //任务并没有把l..r包住，需要将任务下发
        int mid = (l+r) >> 1 ;
        //首先需要下发之前攒的所有任务
        pushDown(rt,mid - l + 1,r-mid);
        //左子树是否需要接受任务
        if(taskLeft<mid){
            add(taskLeft,taskRight,addTarget,l,mid,rt<<1);
        }
        //右子树是否需要接受任务
        if(taskRight>mid){
            add(taskLeft,taskRight,addTarget,mid+1,r,(rt<<1)| 1);
        }
        //左子树和右子树执行完任务之后，汇总当前信息（累加和信息）
        pushUp(rt);
    }

    /**
     * taskLeft到taskRight范围上的元素更新尾target
     * @param taskLeft 任务左边界
     * @param taskRight 任务右边界
     * @param target 更新目标值
     * @param l
     * @param r
     * @param rt
     */
    public void update(int taskLeft,int taskRight,int target,int l,int r,int rt){
        //如果任务区间包含当前区间
        if(taskLeft<=l && taskRight>=r){
            update[rt] = true ;
            change[rt] = target;
            sum[rt] = (r-l+1) * target ;
            lazy[rt] = 0 ; //需要将之前积攒的lazy清空
            return ;
        }
        //如果任务区间不包含当前区间，此时无法懒更新
        int mid = (l+r)>>1 ;
        //将之前的任务下发
        pushDown(rt, mid - l + 1, r - mid);

        if(taskLeft<=mid){
            update(taskLeft,taskRight,target,l,mid,rt<<1);
        }

        if(taskRight>mid){
            update(taskLeft,taskRight,target,mid+1,r,(rt<<1)|1);
        }

        pushUp(rt);
    }

    /**
     * 查询taskLeft到taskRight的累加和
     * @param taskLeft
     * @param taskRight
     * @param l
     * @param r
     * @param rt
     * @return
     */
    public int query(int taskLeft,int taskRight,int l,int r,int rt){
        //如果任务包含，则直接返回对应的累加和即可
        if(taskLeft<=l && taskRight>=r){
            return sum[rt];
        }
        //如果任务不包含
        int mid = (l+r)>>1 ;
        //首先下发积攒的任务
        pushDown(rt,mid-l+1,r-mid);

        int ans = 0 ;

        if(taskLeft<=mid){
            ans += query(taskLeft,taskRight,l,mid,rt<<1);
        }

        if(taskRight>mid){
            ans += query(taskLeft,taskRight,mid+1,r,(rt<<1)|1);
        }

        return ans ;
    }
}
