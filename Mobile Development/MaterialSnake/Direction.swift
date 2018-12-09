import Foundation


///  2D Directions - For moving objects in our system
enum DirectionType {
	case Left
	case Right
	case Up
	case Down
	case UpRight
	case UpLeft
	case DownRight
	case DownLeft
}


extension DirectionType {

	/// The opposite direction type of the current direction type.
	var opposite: DirectionType {
		switch self {
			case .Left: return .Right
			case .Right: return .Left
			case .Up: return .Down
			case .Down: return .Up
			case .UpLeft: return .DownRight
			case .UpRight: return .DownLeft
			case .DownLeft: return .UpRight
			case .DownRight: return .UpLeft
		}
	}

}

enum DirectionCategory {

	/// Includes Left, Right, Up, Down
	case Simple

	/// Includes UpRight, UpLeft, DownRight, DownLeft
	case Complex

	/// Includes All Direction Types -- See Simple & Complex
	case All

}


/// 2D Direction - For moving objects in our system.
struct Direction: Equatable {

	var type: DirectionType

	/// Internal Representation for Direction

	private struct DirectionPoint {
		var x: Int
		var y: Int
	}

	/// Internal Values for X & Y -> DirectionPoint
	private var value: Dictionary<DirectionType, DirectionPoint> = [
			.Left: DirectionPoint(x: -1, y: 0),
			.Right: DirectionPoint(x: 1, y: 0),
			.Up: DirectionPoint(x: 0, y: -1),
			.Down: DirectionPoint(x: 0, y: 1),
			.UpRight: DirectionPoint(x: 1, y: -1),
			.UpLeft: DirectionPoint(x: -1, y: -1),
			.DownRight: DirectionPoint(x: 1, y: 1),
			.DownLeft: DirectionPoint(x: -1, y: 1)
	]

	/**
	 The X value of our direction point.

	 ## Possible Values & Meanings ##
	 ````
	 * value -1 : The object will move to the left side of the screen.
	 * value  0 : The Object will move the the top/bottom of the screen.
	 * value  1 : The Object will move the the right side of the screen.
	 * value nil : The type of the direction is undefined.
	 ````
	 */
	var x: Int? {
		return value[type]?.x
	}

	/**
	 The Y value of our direction point.

	 ## Possible Values & Meanings ##
	 ````
	 * value -1 : The object will move to the top of the screen.
	 * value  0 : The Object will move the the left/right side of the screen.
	 * value  1 : The Object will move the the bottom of the screen.
	 * value nil : The type of the direction is undefined;.
	 ````
	 */
	var y: Int? {
		return value[type]?.y
	}

	init(type: DirectionType) {
		self.type = type
	}

	init() {
		self.type = DirectionType.Right
	}

	public static func ==(left: Direction, right: Direction) -> Bool {
		return left.type == right.type
	}

	/**
	 Tests if we move or not an object using an angle of 180 degrees.

	 - parameter old: The old direction type of the object
	 - parameter new: The new direction type of the object
	 - returns true if we don't move the object with an angle of 180 degrees. (pi rad)
	 */
	static func isPossible(old: DirectionType, new: DirectionType) -> Bool {
		if old.opposite == new {
			return false
		}
		return true
	}

	/**
	 Returns a random direction based on category (Simple, Complex or All).

	 ## Possible Values & Meanings ##
	 ````
	 * value DirectionCategory.Simple : Includes Left, Right, Up, Down
	 * value DirectionCategory.Complex: Includes UpRight, UpLeft, DownRight, DownLeft
	 * value DirectionCategory.All    : Includes All Direction Types
	 ````

	 - parameter category: The direction's category
	 - returns: New random direction based on category
	 */
	static func random(category: DirectionCategory = DirectionCategory.All) -> Direction {
		var type = DirectionType.Right
		var index: UInt32 = 0
		switch category {
			case .All: index = arc4random_uniform(7)
			case .Complex: index = arc4random_uniform(3) + 4
			case .Simple: index = arc4random_uniform(3)
		}
		switch index {
			case 0: type = DirectionType.Right
			case 1: type = DirectionType.Left
			case 2: type = DirectionType.Up
			case 3: type = DirectionType.Down
			case 4: type = DirectionType.UpRight
			case 5: type = DirectionType.UpLeft
			case 6: type = DirectionType.DownLeft
			case 7: type = DirectionType.DownRight
			default: type = DirectionType.Right
		}
		return Direction(type: type)
	}

}
