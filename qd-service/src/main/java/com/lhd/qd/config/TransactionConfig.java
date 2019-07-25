package com.lhd.qd.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 事务配置
 *
 * @author lhd
 */
@Aspect
@Configuration
public class TransactionConfig {

    private static final int TRANSACTION_METHOD_TIMEOUT = 20;
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.lhd.qd.module..service.*Service.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        // 只读事务
        RuleBasedTransactionAttribute readOnlyTransaction = new RuleBasedTransactionAttribute();
        readOnlyTransaction.setReadOnly(true);
        readOnlyTransaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        // 支持当前事务，如果当前没有事务，就新建一个事务
        RuleBasedTransactionAttribute requiredTransaction = new RuleBasedTransactionAttribute();
        requiredTransaction.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTransaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTransaction.setTimeout(TRANSACTION_METHOD_TIMEOUT);

        Map<String, TransactionAttribute> txMap = new HashMap<>(7);
        txMap.put("save*", requiredTransaction);
        txMap.put("update*", requiredTransaction);
        txMap.put("remove*", requiredTransaction);
        txMap.put("is*", readOnlyTransaction);
        txMap.put("get*", readOnlyTransaction);
        txMap.put("list*", readOnlyTransaction);
        txMap.put("page*", readOnlyTransaction);
        txMap.put("count*", readOnlyTransaction);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}