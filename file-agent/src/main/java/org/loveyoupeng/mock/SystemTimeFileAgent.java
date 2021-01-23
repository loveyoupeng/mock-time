package org.loveyoupeng.mock;

import static net.bytebuddy.matcher.ElementMatchers.isMethod;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.none;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static net.bytebuddy.matcher.ElementMatchers.takesNoArguments;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.NoOp;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy;
import net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy.Default;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class SystemTimeFileAgent {

  public static void premain(final String args, final Instrumentation instrumentation) {
    new AgentBuilder.Default().ignore(none())
        // .with(Listener.StreamWriting.WithErrorsOnly.StreamWriting.toSystemError())
        .enableNativeMethodPrefix("real")
        .with(Default.REBASE)
        .with(RedefinitionStrategy.REDEFINITION)
        .with(NoOp.INSTANCE)
        .type(ElementMatchers.is(System.class))
        .transform((builder, typeDescription, classLoader, module) ->
            builder.method(isMethod()
                .and(isStatic())
                .and(returns(long.class))
                .and(takesNoArguments())
                .and(named("currentTimeMillis")))
                .intercept(Advice.to(SystemTimeFileDelegate.class)
                    .wrap(SuperMethodCall.INSTANCE))
        ).installOn(instrumentation);
  }
}