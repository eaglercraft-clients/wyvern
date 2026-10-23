# Eaglercraft support

## The problem

Eaglercraft clients talk to servers over plugin channels with names such as `EAG|Skins-1.8`. Until 1.12.2 a channel name
was free text. From 1.13 on it must be a lower-case `namespace:path` identifier; `|` and capital letters are illegal.
ViaVersion's 1.12.2 <-> 1.13 translation knows a fixed list of old channels and otherwise checks whether the old name is
already a valid identifier. `EAG|...` is not, so the message is dropped (ViaBackwards logs
`Ignoring serverbound plugin message with channel: ...` when `log-other-conversion-warnings` is on). That matches what
the EaglerXServer maintainers describe: plugin-message restrictions stop Eaglercraft clients of protocol V4 and older from
working properly on Spigot/Paper newer than 1.12.2.

## What this fork does

In `protocol/v1_13to1_12_2` (the hop where channel naming changes), before ViaVersion's own mapping is consulted:

| Direction | Channel | Becomes |
| --- | --- | --- |
| client -> server | `EAG|Skins-1.8` | `eag:skins-1.8` |
| server -> client | `eag:skins-1.8` | `EAG|Skins-1.8` (the spelling the client used) |

`REGISTER` / `UNREGISTER` lists are rewritten the same way. The connection remembers each name the client used, because
upper-case letters cannot be recovered from the identifier. A server -> client message on an `eag:` channel the client has
never used is left to ViaVersion's normal handling.

Code: `common/src/main/java/org/eaglercraftclients/wyvern/eagler/`.

## Settings (`wyvern.yml`)

- `eagler-channel-bridge` (default `true`): turn the bridge on or off.
- `eagler-extra-channels` (default empty): comma separated extra pre-1.13 channel names to treat like `EAG|...`.

## What the server side has to do

The server (or the server plugin) must use the `eag:` identifiers: register `eag:skins-1.8`, not `EAG|Skins-1.8`. This is a
naming convention of this fork; a plugin that does not use it will not see the traffic. Behind a proxy that terminates the
Eaglercraft connection itself (EaglerXServer on Velocity or BungeeCord), the Eaglercraft channels never reach the backend and
this bridge is not needed for them.

## Not done / not verified

- **Not tested against a real Eaglercraft client and server.** The mapping rules are unit tested
  (`EaglerChannelsTest`); the packet handling compiles in CI but has not seen live traffic.
- The exact set of channel names each Eaglercraft client version uses has not been checked against the clients. The
  bridge is prefix based (`EAG|`) on purpose so it does not depend on a hard-coded list.
- Eaglercraft 1.5.2 clients use the pre-Netty protocol and are outside ViaVersion/ViaBackwards; they need their own
  translator (EaglerXServer's `EaglerXRewind` does that).
- Other Eaglercraft specifics (handshake, skins/capes payload formats, voice) are not handled here.
