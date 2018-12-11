
import XCTest
@testable import MaterialSnake

class DirectionTest: XCTestCase {

    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }
    
    func testEqual() {
        let left = Direction(type: .Left)
        let test = Direction(type: .Left)
        XCTAssert(left == test)
        let right = Direction(type: .Right)
        XCTAssert(!(test == right))
        for _ in 1...100 {
            let directionA = Direction.random()
            let directionB = Direction.random()
            if directionA.type == directionB.type {
                XCTAssert(directionA == directionB)
            } else {
                XCTAssert(!(directionA == directionB))
            }
        }
    }
    
    func testPossible() {
        for _ in 1...100 {
            let direction = Direction.random()
            let test = Direction.random()
            if Direction.isPossible(old: direction.type, new: test.type) {
                XCTAssert(direction.type.opposite != test.type)
            }
            
        }
    }
    
    func testXY() {
        for _ in 1...100 {
            let direction = Direction.random()
            // Test X
            if direction.type == .Left || direction.type == .DownLeft ||
                    direction.type == .UpLeft {
                XCTAssert(direction.x == -1)
            } else if direction.type == .Up || direction.type == .Down {
                XCTAssert(direction.x == 0)
            } else {
                XCTAssert(direction.x == 1)
            }
            // Test Y
            if direction.type == .UpRight || direction.type == .Up ||
                direction.type == .UpLeft {
                XCTAssert(direction.y == -1)
            } else if direction.type == .Left || direction.type == .Right {
                XCTAssert(direction.y == 0)
            } else {
                XCTAssert(direction.y == 1)
            }
        }
    }
    
    func testRandom() {
        for _ in 1...100 {
            let complex: [DirectionType] = [.UpRight, .DownRight, .UpLeft, .DownLeft]
            let simple: [DirectionType] = [.Left, .Right, .Up, . Down]
            let directionComplex = Direction.random(category: .Complex)
            let directionSimple = Direction.random(category: .Simple)
            XCTAssert(complex.contains(directionComplex.type))
            XCTAssert(!complex.contains(directionSimple.type))
            XCTAssert(!simple.contains(directionComplex.type))
            XCTAssert(simple.contains(directionSimple.type))
        }
    }
    
}
