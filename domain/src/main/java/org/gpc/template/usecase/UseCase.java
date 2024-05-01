package org.gpc.template.usecase;

import org.gpc.template.kernel.Pet;

import java.util.Optional;

@FunctionalInterface
public interface UseCase<T,R> {
    R execute(T command);
}
