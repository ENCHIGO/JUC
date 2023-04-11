package com.enchigo.juc.learning;

import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.types.TFloat32;

public class HelloTensorFlow {


    public static void main(String[] args) throws Exception {
        System.out.println("Hello TensorFlow " + TensorFlow.version());

        SavedModelBundle savedModelBundle = SavedModelBundle.load("/Users/enchigo/IdeaProjects/JUC/juc-learning/src/main/resources/my_model","serve");
        Graph graph = savedModelBundle.graph();



        Tensor input
                 = TFloat32.scalarOf(1f);
        Tensor tensor = savedModelBundle.session().runner()
                .feed("dense_input", input)
                .fetch("dense")
                .run().get(0);

        System.out.println(tensor.toString());
    }



}
