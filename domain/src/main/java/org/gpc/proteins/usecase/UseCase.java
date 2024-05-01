package org.gpc.proteins.usecase;

@FunctionalInterface
public interface UseCase<T,R> {
    R execute(T command);
}
