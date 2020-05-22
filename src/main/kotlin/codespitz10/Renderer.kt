/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codespitz10

/**
 * CompositeTask 를 소비하기 위해서는 순회자가 필요.
 * 순회는 제어임.
 * 순회하고자 하는 제어는 하나로 통일하고, 그 때 해야할 일은 라이프사이클 객체로 만듬 (제어의 역전, 프레임워크)
 */
class Renderer(
    /**
     * OOP 를 할 때, 생각해야하는 점 중 하나는 인스턴스를 생성하는 컨텍스트인지 소유하고 재활용하고 컨텍스트인지 확인해야함
     * 언제나 안전한건 생성하는 거임
     *
     * 고로, function 을 받도록 처리함
     * Visitor 상태 초기화, 동시성 문제에 안전함
     */
    private val visitorFactory: () -> Visitor
) {

    /**
     * public 에는 좋은 함수만 노출하라.
     * 나머지는 숨겨라.
     */
    fun render(report: TaskReport) {
        // 트랜잭션..
        // 변수 선언은 트랜잭션에 관여하겠다는 거임
        render(visitorFactory.invoke(), report, 0)
    }

    companion object {

        private fun render(visitor: Visitor, report: TaskReport, depth: Int) {
            visitor.onDrawTask(report.task, depth)
            report.childTaskReports.forEach { render(visitor, it, depth + 1) }
            visitor.onEnd(depth)
        }

    }

}