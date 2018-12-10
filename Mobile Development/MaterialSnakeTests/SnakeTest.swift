
import XCTest
@testable import MaterialSnake


class SnakeTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }

    override func tearDown() {
        super.tearDown()
    }

    func testMove() {
        for direction in [DirectionType.Down, DirectionType.Right, DirectionType.Left, DirectionType.Up] {
            let snake = Snake(position: Position(x: 60, y: 60), size: Size(width: 20, height: 20))
            let test = Snake(position: Position(x: 60, y: 60), size: Size(width: 20, height: 20))
            test.direction = Direction(type: direction)
            test.move()
            for index in 1..<snake.body.count - 1 {
                switch direction {
                case .Down:
                    XCTAssert(test.position.x == snake.position.x)
                    XCTAssert(test.position.y == snake.position.y + 20)
                    XCTAssert(test.body[index] == snake.body[index-1])
                case .Left:
                    XCTAssert(test.position.x == snake.position.x - 20)
                    XCTAssert(test.position.y == snake.position.y)
                    XCTAssert(test.body[index] == snake.body[index-1])
                case .Up:
                    XCTAssert(test.position.x == snake.position.x)
                    XCTAssert(test.position.y == snake.position.y - 20)
                    XCTAssert(test.body[index] == snake.body[index-1])
                case .Right:
                    XCTAssert(test.position.x == snake.position.x + 20)
                    XCTAssert(test.position.y == snake.position.y)
                    XCTAssert(test.body[index] == snake.body[index-1])
                default: break
                }
            }
        }
    }

    func testIncrementTail() {
        for direction in [DirectionType.Down, DirectionType.Right, DirectionType.Left, DirectionType.Up] {
            let snake = Snake(position: Position(x: 60, y: 60), size: Size(width: 20, height: 20))
            let last = snake.body.last!
            snake.direction = Direction(type: direction)
            snake.incrementTail()
            XCTAssert(snake.body.count == 4)
            let new = snake.body.last!
            switch direction {
            case .Down:
                XCTAssert(new.x == last.x)
                XCTAssert(new.y == last.y - 20)
            case .Left:
                XCTAssert(new.x == last.x + 20)
                XCTAssert(new.y == last.y)
            case .Up:
                XCTAssert(new.x == last.x)
                XCTAssert(new.y == last.y + 20)
            case .Right:
                XCTAssert(new.x == last.x - 20)
                XCTAssert(new.y == last.y)
            default: break
            }
        }
    }

}
