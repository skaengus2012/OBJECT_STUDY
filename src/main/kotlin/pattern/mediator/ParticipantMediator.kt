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

class ParticipantMediator : Mediator {

    private var view: ViewCommander? = null
    private var book: ViewCommander? = null
    private var display: Displayable? = null
    private var search: ViewCommander? = null

    override fun book() {
        book?.setEnabled(false)
        view?.setEnabled(true)
        search?.setEnabled(true)
        display?.showState("booking...")
    }

    override fun view() {
        book?.setEnabled(true)
        view?.setEnabled(false)
        search?.setEnabled(true)
        display?.showState("view...")
    }

    override fun search() {
        book?.setEnabled(true)
        view?.setEnabled(true)
        search?.setEnabled(false)
        display?.showState("search...")
    }

    override fun registerView(view: ViewCommander) {
        this.view = view
    }

    override fun registerBook(book: ViewCommander) {
        this.book = book
    }

    override fun registerDisplay(display: Displayable) {
       this.display = display
    }

    override fun registerSearch(search: ViewCommander) {
        this.search = search
    }
}