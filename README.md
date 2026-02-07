# SimpleJoinMessage 

SimpleJoinMessage is a professional-grade, lightweight solution for managing player connection messages on modern Minecraft servers. Developed by **Septy Studios**, it focuses on high performance, clean configuration, and modern formatting standards.

## 🚀 Features

* **Modern Formatting:** Full support for Kyori MiniMessage (Hex colors, Gradients, Hover effects).
* **Legacy Support:** Compatible with traditional ampersand (`&`) color codes.
* **Placeholder System:** Native support for `<player>`, `%online%`, and `%max%`.
* **Toggleable Components:** Enable or disable join/quit messages independently via config.
* **Performance Focused:** Zero-impact footprint, utilizing asynchronous-friendly logic and the latest Paper API.

## 🛠️ Configuration

The `config.yml` is designed to be intuitive and fully customizable:

```yaml
settings:
  use-minimessage: true

join:
  enabled: true
  default-message: "<gray>[<green>+</green>] <white><player> <gray>joined the server."
  worlds:
    world_nether: "<red>🔥 <white><player> <gray>entered the depths of the nether."

quit:
  enabled: true
  default-message: "<gray>[<red>-</red>] <white><player> <gray>left the server."
