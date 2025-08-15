# Mystic Wands - Minecraft Plugin

A powerful and magical Minecraft plugin that adds custom wands with various spells and a mana system!

## 🌟 Features

### ✨ Magical Wands
- **Fire Wand**: Launch devastating fireballs
- **Lightning Wand**: Strike enemies with lightning
- **Teleport Wand**: Instantly teleport to your target location
- **Healing Wand**: Restore health with magical energy
- **Ice Wand**: Freeze areas and slow enemies
- **Destruction Wand**: Create powerful explosions

### 🔮 Mana System
- Each player has a mana bar displayed as a boss bar
- Mana automatically regenerates over time
- Different spells consume different amounts of mana
- Mana bar changes color based on current mana level

### ⚡ Spell Effects
- Beautiful particle effects for each spell
- Immersive sound effects
- Area-of-effect spells with visual feedback
- Cooldown system to prevent spell spam

## 📋 Commands

- `/wand give <type>` - Give yourself a specific wand
- `/wand list` - View all available wand types
- `/wand mana` - Check your current mana

## 🔧 Installation

1. Download the plugin JAR file
2. Place it in your server's `plugins` folder
3. Restart your server
4. The plugin will generate a `config.yml` file for customization

## 🎮 Usage

1. Use `/wand give fire` to get a Fire Wand
2. Right-click with the wand to cast spells
3. Watch your mana bar to manage your magical energy
4. Each spell has a cooldown period

## ⚙️ Configuration

The plugin includes a comprehensive `config.yml` file where you can:
- Adjust mana settings (max mana, regen rate)
- Modify spell properties (damage, range, cooldown)
- Enable/disable effects and sounds
- Customize spell costs

## 🛡️ Permissions

- `mysticwands.give` - Allows giving wands (default: op)
- `mysticwands.use` - Allows using wands (default: true)

## 🔮 Spell Details

### Fire Wand (20 Mana)
- Launches explosive fireballs
- 3-second cooldown
- Creates fire and explosion damage

### Lightning Wand (25 Mana)
- Strikes lightning at target location
- 5-second cooldown
- 50-block range

### Teleport Wand (30 Mana)
- Teleports to target block
- 10-second cooldown
- 100-block range
- Portal particle effects

### Healing Wand (15 Mana)
- Restores up to 6 hearts
- 8-second cooldown
- Heart particle effects

### Ice Wand (20 Mana)
- Freezes area and slows entities
- 6-second cooldown
- Creates ice blocks and snow

### Destruction Wand (40 Mana)
- Creates powerful explosions
- 15-second cooldown
- Most expensive but most powerful

## 🚀 Building from Source

1. Clone this repository
2. Run `mvn clean package`
3. The compiled JAR will be in the `target` folder

## 📝 Requirements

- Minecraft Server 1.16+
- Spigot/Paper/Bukkit
- Java 8+

## 🐛 Support

If you encounter any issues or have suggestions, please create an issue on the repository!

Enjoy your magical adventures! ✨🔮⚡