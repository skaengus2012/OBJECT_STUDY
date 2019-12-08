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

package pattern.mediator

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.JPanel

class MediatorDemo : JFrame(), ActionListener {

    private val mediator: Mediator = ParticipantMediator()

    init {
        val panel = JPanel()
        panel.add(Book(this, mediator))
        panel.add(Search(this, mediator))
        panel.add(View(this, mediator))

        contentPane.add(Display(mediator), "North")
        contentPane.add(panel, "South")
        setSize(200, 200)

        isVisible = true
    }

    override fun actionPerformed(e: ActionEvent?) {
        (e?.source as? Command)?.execute()
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            MediatorDemo()
        }
    }
}