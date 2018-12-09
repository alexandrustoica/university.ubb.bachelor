import Foundation


enum GameStatus {

	/// The game is still running in the system.
	case Running

	/// The game is over due to player's actions or internal fatal error
	case Terminated

}


enum GameType {

	/// Game without Border Restrictions
	case Free

	/// Game with Border Restrictions
	case Classic

}


/// GameType Converter For Local DB

extension GameType {

	func toString() -> String {
		switch self {
			case .Free: return "Free"
			case .Classic: return "Classic"
		}
	}

	static func fromString(_ string: String) -> GameType? {
		switch string {
			case "Free": return GameType.Free
			case "Classic": return GameType.Classic
			default: return nil
		}
	}

}


struct Game: Equatable {

	/// The Game's Type [Rules Identifier]
	private (set) var type: GameType
	var score: Int
	var level: Int

	/**
	 - parameter type: The game's type [Free or Classic] -- See GameType Documentation
	 - note: Default values: score = 0 && level = 1
	*/
	init(type: GameType) {
		self.type = type
		self.score = 0
		self.level = 1
	}

	/// note: Default values: score = 0 && level = 1 && type = .Free
	init() {
		type = GameType.Free
		score = 0
		level = 1
	}

	static func ==(left: Game, right: Game) -> Bool {
		return left.type == right.type && left.score == right.score && left.level == right.score
	}

}
