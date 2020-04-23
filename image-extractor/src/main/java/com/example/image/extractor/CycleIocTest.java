package com.example.image.extractor;

import org.springframework.stereotype.Component;

public class CycleIocTest {
}

@Component
class A {
    private B b;

    public A() {
    }

    public A(B b) {
        this.b = b;
    }
}

@Component
class B {
    private C c;

    public B() {
    }

    public B(C c) {
        this.c = c;
    }
}

@Component
class C {
    private A a;

    public C(A a) {
        this.a = a;
    }
}
