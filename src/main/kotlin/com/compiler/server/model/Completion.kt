package com.compiler.server.model

data class Completion(val text: String, val displayText: String, val tail: String, val icon: String)