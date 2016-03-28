package qyl.com.opengldemos.shape;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class Particle {
    public boolean active; // 是否激活
    public float life;     // 粒子生命
    public float fade;     // 衰减速度

    public float r;        // 红色值
    public float g;        // 绿色值
    public float b;        // 蓝色值

    public float x;        // X位置
    public float y;        // Y位置
    public float z;        // Z位置

    public float xi;       // X方向
    public float yi;       // Y方向
    public float zi;       // Z方向

    public float xg;       // X 方向重力加速度
    public float yg;       // Y 方向重力加速度
    public float zg;       // Z 方向重力加速度
}
