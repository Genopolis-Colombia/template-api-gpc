package org.gpc.proteins.handler;

@FunctionalInterface
public interface Handler<T,R> {
    R handle(T command);
}
