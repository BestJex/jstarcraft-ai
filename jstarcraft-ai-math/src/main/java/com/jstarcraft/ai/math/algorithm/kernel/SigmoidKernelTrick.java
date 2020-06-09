package com.jstarcraft.ai.math.algorithm.kernel;

import org.apache.commons.math3.util.FastMath;

import com.jstarcraft.ai.math.structure.DefaultScalar;
import com.jstarcraft.ai.math.structure.MathScalar;
import com.jstarcraft.ai.math.structure.vector.MathVector;

/**
 * Sigmoid Kernel(Sigmoid核)
 * 
 * @author Birdy
 *
 */
public class SigmoidKernelTrick implements KernelTrick {

    private float a;

    private float c;

    public SigmoidKernelTrick(float a, float c) {
        this.a = a;
        this.c = c;
    }

    @Override
    public float calculate(MathVector leftVector, MathVector rightVector) {
        MathScalar scalar = DefaultScalar.getInstance();
        return (float) FastMath.tanh(a * scalar.dotProduct(leftVector, rightVector).getValue() + c);
    }

}
