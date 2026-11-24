# Changelog

## 1.0.3
### Fixed
- Golden Bow shows its draw animation again (the original's 4 pull textures).
- Sleeping effect has its icon in the inventory again (as in the original).
- Dendroids can drop their rare Dendroid Eye again (2.5% when killed by a player, more with Looting).
- Server tested: all 30 mobs spawn and tick without errors.

## 1.0.2
### Changed
- The original mod file is now downloaded automatically on first start (needs ArchaicLib 1.42.0+): sources and mirrors are listed in `config/archaiclib/originals/` and can be edited there. If the download fails, a banner on the main menu links to the manual download.

## 1.0.1
### Changed
- Performance: uses ArchaicLib 1.40.0 FastRandom instead of java.util.Random, plus the 1.40.0 cached block-state / allocation-free legacy world calls. Requires ArchaicLib 1.40.0+.

## 1.0.0
- First release of the 1.12.2 (Cleanroom) port of Myths and Monsters Mod by HoopAWolf. Requires ArchaicLib 1.35.0+.
