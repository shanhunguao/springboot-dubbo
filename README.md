# springboot-dubbo
主要参考文档:
构建微服务:https://zhuanlan.zhihu.com/p/51241558
springboot集成mybatis-generator:http://www.apigo.cn/2018/08/14/springboot7/
dubbo监控:https://www.cnblogs.com/qlqwjy/p/10561601.html
maven打成的war包：dubbo-admin-2.5.10.war


使用 SpringBoot+Dubbo 搭建一个简单分布式服务
我们先来简单的了解一下这样几个概念：Dubbo、RPC、分布式、由于本文的目的是带大家使用SpringBoot+Dubbo 搭建一个简单的分布式服务，所以这些概念我只会简单给大家普及一下，不会做深入探究。

什么是分布式?
分布式或者说 SOA 分布式重要的就是面向服务，说简单的分布式就是我们把整个系统拆分成不同的服务然后将这些服务放在不同的服务器上减轻单体服务的压力提高并发量和性能。比如电商系统可以简单地拆分成订单系统、商品系统、登录系统等等。

我们可以使用 Dubbo作为分布式系统的桥梁，那么什么是 Dubbo 呢？

什么是 Duboo？
Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC 框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。简单来说 Dubbo 是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。

Dubbo 目前已经有接近 23k 的 Star ，Dubbo的Github 地址：https://github.com/apache/incubator-dubbo。另外，在开源中国举行的2018年度最受欢迎中国开源软件这个活动的评选中，Dubbo 更是凭借其超高人气仅次于 vue.js 和 ECharts 获得第三名的好成绩。

Dubbo 是由阿里开源，后来加入了 Apache 。正式由于 Dubbo 的出现，才使得越来越多的公司开始使用以及接受分布式架构。

下面我们简单地来看一下 Dubbo 的架构，加深对 Dubbo 的理解。

Dubbo 架构
下面我们再来看看 Dubbo 的架构，我们后面会使用 zookeeper 作为注册中心，这也是 Dubbo 官方推荐的一种方式。

上述节点简单说明：

Provider 暴露服务的服务提供方
Consumer 调用远程服务的服务消费方
Registry 服务注册与发现的注册中心
Monitor 统计服务的调用次数和调用时间的监控中心
Container 服务运行容器
调用关系说明：

服务容器负责启动，加载，运行服务提供者。
服务提供者在启动时，向注册中心注册自己提供的服务。
服务消费者在启动时，向注册中心订阅自己所需的服务。
注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。
我们在讲 Dubbo 的时候提到了 Dubbo 实际上是一款 RPC 框架，那么RPC 究竟是什么呢？相信看了下面我对 RPC 的介绍你就明白了！

什么是 RPC？
RPC（Remote Procedure Call）—远程过程调用，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。比如两个不同的服务A,B部署在两台不同的机器上，那么服务 A 如果想要调用服务 B 中的某个方法该怎么办呢？使用 HTTP请求 当然可以，但是可能会比较慢而且一些优化做的并不好。 RPC 的出现就是为了解决这个问题。

为什么要用 Dubbo？
如果你要开发分布式程序，你也可以直接基于 HTTP 接口进行通信，但是为什么要用 Dubbo呢？

我觉得主要可以从 Dubbo 提供的下面四点特性来说为什么要用 Dubbo：

负载均衡——同一个服务部署在不同的机器时该调用那一台机器上的服务
服务调用链路生成——服务之间互相是如何调用的
服务访问压力以及时长统计——当前系统的压力主要在哪里，如何来扩容和优化
服务降级——某个服务挂掉之后调用备用服务

测试分支-冲突提交


