TODO:
    守卫和追踪怪
    一定距离丢失
    追踪ai
    刷怪笼



ThreadControl:

    repaint 在 appMain 里 通过 ScheduledExecutorService 周期性调用 repaintThread实现

    每一层 Floor 拥有一个ExecutorService，当进入Floor时将当前楼层所有creature的creatureThread加入，退出楼层时shutdonw

    每一个Creature拥有一个 CreatureThread，控制creature的活动


Status Bar
FLOOR 0

Player: @
hp:
attack:
defence:
items:

Info Bar

@ monster
  attack: 10 | defence:5 | visionRadius:2 
——————————————————————————————————————————


遭遇问题：
    maven打包后的路径问题
    maven打包后jdk不支持javaFX.utils.pair的问题