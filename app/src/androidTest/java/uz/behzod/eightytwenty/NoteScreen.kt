package uz.behzod.eightytwenty

import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class NoteScreen: Screen<NoteScreen>() {

    val text = KTextView {
        withId(R.id.tv_note)
    }
}