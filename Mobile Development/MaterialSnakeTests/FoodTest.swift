
import XCTest
@testable import MaterialSnake


class FoodTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    func testIsEaten() {
        var snake = Snake(position: Position(x: 60, y: 60), size: Size(width: 20, height: 20))
        let food = Food(position: Position(x: 80, y: 60), size: Size(width: 20, height: 20), type: FoodType.Normal)
        snake.move()
        XCTAssert(food.isEaten(by: snake.position) == true)
        snake = Snake(position: Position(x: 40, y: 60), size: Size(width: 20, height: 20))
        let bonus = Food(position: Position(x: 80, y: 40), size: Size(width: 20, height: 20), type: FoodType.Bonus)
        snake.move()
        XCTAssert(bonus.isEaten(by: snake.position) == false)
        snake.move()
        XCTAssert(bonus.isEaten(by: snake.position) == true)

    }

}
