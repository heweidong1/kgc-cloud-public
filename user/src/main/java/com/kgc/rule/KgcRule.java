package com.kgc.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class KgcRule extends AbstractLoadBalancerRule
{
    Random rand;

    private int nowIndex=-1;

    private int lastIndex=-1;

    private int skipIndex=-1;

    public KgcRule()
    {
        rand=new Random();
    }

    /**
     * 伪随机，当一个微服务连续被调用两次，第三次如果还是他，那么咱们就在随机一次
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            int index = rand.nextInt(serverCount);
            if(index==skipIndex)
            {
                do{
                    index=rand.nextInt(serverCount);
                }while (index==skipIndex);

            }

            skipIndex=-1;

            nowIndex=index;

            if(nowIndex==lastIndex)
            {
                skipIndex=nowIndex;
            }
            lastIndex=nowIndex;
            server = upList.get(index);

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}
