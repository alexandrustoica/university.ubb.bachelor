
import Foundation


class Snake: SpaceEntity {

    /// The snake's position on the map.
    var position: Position

    /// The snake's head size. ** Recommended ** Use map's exact cell size
    var size: Size

    /// The snake's movement direction [Right, Left, Up, Down]
    var direction: Direction

    /// The snake's tail: array of positions for each part of the body
    private(set) var tail: [Position]

    /// The snake's body [head + tail]: array of positions for each part of the snake
    var body: [Position] {
        get {
            var result = [Position]()
                result.append(position)
                result.append(contentsOf: tail)
            return result
        }
    }

    /**
     - parameter position: The snake's position on the map
     - parameter size: The size of the snake [head's size]
    */
    init(position: Position, size: Size) {
        self.position = position
        self.size = size
        self.direction = Direction(type: DirectionType.Right)
        self.tail = [Position(x: position.x - size.width, y: position.y),
                     Position(x: position.x - 2 * size.width, y: position.y)]
    }

    deinit {
        tail.removeAll()
    }

    /// Moves the snake to the next position (next column/row on the map).
    func move() {
        for index in (0...tail.count - 2).reversed() {
            tail[index + 1] = tail[index]
        }
        tail[0] = position
        position = Position(x: position.x + Double(direction.x!) * size.width,
                y: position.y + Double(direction.y!) * size.height)
    }

    /// Increments the number of elements in the snake's tail by one.
    func incrementTail() {
        let last = tail[tail.count - 1]
        switch direction.type {
        case .Down:
            tail.append(Position(x: last.x, y: last.y - size.height))
        case .Up:
            tail.append(Position(x: last.x, y: last.y + size.height))
        case .Left:
            tail.append(Position(x: last.x + size.width , y: last.y))
        case .Right:
            tail.append(Position(x: last.x - size.width , y: last.y))
        default: break
        }
    }

    /// Checks if the snake eats itself.
    func isSelfEating() -> Bool {
        for element in tail {
            if position.isApproxEqual(element) {
                return true
            }
        }
        return false
    }

    /// Checks if object (defined by position) is on/under snake body.
    func isOnSnake(position: Position) -> Bool {
        for element in body {
            if position.isApproxEqual(element) {
                return true
            }
        }
        return false
    }

}