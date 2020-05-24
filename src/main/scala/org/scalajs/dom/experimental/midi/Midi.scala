package org.scalajs.dom.experimental.midi

import org.scalajs.dom.Event
import org.scalajs.dom.raw.EventInit

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}

/**
 * This dictionary contains optional settings that may be provided to the requestMIDIAccess request.
 *
 * @see [[https://www.w3.org/TR/webmidi/#midioptions-dictionary]]
 */
trait MIDIOptions {
  var sysex: js.UndefOr[Boolean] = js.undefined
}

/**
 * This interface provides the methods to list MIDI input and output devices, and obtain access to an individual device.
 *
 * @see [[https://www.w3.org/TR/webmidi/#midiaccess-interface]]
 */
@js.native
trait MIDIAccess extends js.Object {

  /** The MIDI input ports available to the system. */
  val inputs = ??? // TODO: how to do these maps?

  /** The MIDI output ports available to the system. */
  val ouputs = ???

  /** The handler called when a new port is connected or an existing port changes the state attribute. */
  var onstatechange: js.Function1[MIDIConnectionEvent, Any] = js.native

  /** Whether system exclusive support is enabled on this MIDIAccess. */
  val sysexEnabled: Boolean = js.native
}

@js.native
@JSGlobal
class MIDIConnectionEvent(
    typeArg: String,
    init: js.UndefOr[MIDIConnectionEventInit] = js.undefined
) extends Event(typeArg, init) {

  /** The port that has been connected or disconnected. */
  val port: MIDIPort = js.native
}

trait MIDIConnectionEventInit extends EventInit {
  var port: js.UndefOr[MIDIPort] = js.undefined
}

/**
 * This interface represents a MIDI input or output port.
 *
 * @see [[https://www.w3.org/TR/webmidi/#midiport-interface]]
 */
@js.native
trait MIDIPort extends js.Object {

  val id: String = js.native

  def manufacturer: js.UndefOr[String] = js.native

  def name: js.UndefOr[String] = js.native

  @JSName("type")
  def `type`: MIDIPortType = js.native

  def version: js.UndefOr[String] = js.native

  def state: MIDIPortDeviceState = js.native

  def connection: MIDIPortConnectionState = js.native

  def close(): js.Promise[MIDIPort] = js.native

  def open(): js.Promise[MIDIPort] = js.native
}

@js.native
sealed trait MIDIPortType extends js.Any

object MIDIPortType {
  val input = "input".asInstanceOf[MIDIPortType]
  val output = "output".asInstanceOf[MIDIPortType]
}

@js.native
sealed trait MIDIPortDeviceState extends js.Any

object MIDIPortDeviceState {
  val disconnected = "disconnected".asInstanceOf[MIDIPortDeviceState]
  val connected = "connected".asInstanceOf[MIDIPortDeviceState]
}

@js.native
sealed trait MIDIPortConnectionState extends js.Any

object MIDIPortConnectionState {
  val open = "open".asInstanceOf[MIDIPortConnectionState]
  val closed = "closed".asInstanceOf[MIDIPortConnectionState]
  val pending = "pending".asInstanceOf[MIDIPortConnectionState]
}

@js.native
trait MIDIInput extends MIDIPort {

  var onmidimessage = ???

}

trait MIDIOutput extends MIDIPort {

  /** Enqueues the message to be sent ot the corresponding MIDI port. */
  // TODO: `data` should be of type `sequence<octet>` which is a sequence of Uint8.
  def send(data: js.Array[Int], timestamp: js.UndefOr[Double] = js.undefined): Unit = js.native

  /** Clears any pending send data that has not yet been sent from the MIDIOutput's queue. */
  def clear(): Unit

}