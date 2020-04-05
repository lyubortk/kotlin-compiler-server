package com.compiler.server.base

import com.compiler.server.generator.TestProjectRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BaseExecutorTest {
  @Autowired
  private lateinit var testRunner: TestProjectRunner

  fun complete(
    code: String,
    line: Int,
    character: Int,
    completions: List<String>,
    isJs: Boolean = false
  ) = testRunner.complete(code, line, character, completions, isJs)

  fun highlight(code: String) = testRunner.highlight(code)

  fun highlightJS(code: String) = testRunner.highlightJS(code)

  fun run(code: String, contains: String, args: String = "", mode: ExecutorMode) = when (mode) {
    ExecutorMode.SYNCHRONOUS -> testRunner.run(code, contains, args)
    ExecutorMode.STREAMING -> testRunner.runStreaming(code, contains, args)
  }

  fun run(code: List<String>, contains: String, mode: ExecutorMode) = when (mode) {
    ExecutorMode.SYNCHRONOUS -> testRunner.multiRun(code, contains)
    ExecutorMode.STREAMING -> testRunner.multiRunStreaming(code, contains)
  }

  fun runJs(code: String, contains: String, args: String = "") =  testRunner.runJs(code, contains, args)

  fun runJs(code: List<String>, contains: String) =  testRunner.multiRunJs(code, contains)

  fun runWithException(code: String, contains: String, mode: ExecutorMode) = testRunner.runWithException(code, contains)

  fun version() = testRunner.getVersion()
}