package com.example.application.tasks.translator;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

    Map<Integer, Vertex> to = new HashMap<>();
    boolean terminal = false;
}
