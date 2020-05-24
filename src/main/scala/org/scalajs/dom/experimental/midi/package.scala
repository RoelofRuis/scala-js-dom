package org.scalajs.dom.experimental

import org.scalajs.dom

import scala.language.implicitConversions
import scala.scalajs.js

/**
 * The Web MIDI API defines an API supporting the MIDI protocol, enabling web applications to enumerate and select MIDI
 * input and output devices on the client system and send and receive MIDI messages.
 *
 * @see [[https://www.w3.org/TR/webmidi/]]
 */
package object midi {
  implicit def toMidiNavigator(navigator: dom.Navigator): MidiNavigator =
    navigator.asInstanceOf[MidiNavigator]

  @js.native
  trait MidiNavigator extends js.Any {

    /**
     * When invoked, returns a Promise object representing a request for access to MIDI devices on the user's system.
     *
     * @see [[https://www.w3.org/TR/webmidi/#requestmidiaccess]]
     */
    def requestMIDIAccess(options: js.UndefOr[MIDIOptions] = js.undefined): js.Promise[MIDIAccess] = js.native

  }

}
