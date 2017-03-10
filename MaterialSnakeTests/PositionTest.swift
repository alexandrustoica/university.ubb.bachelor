
import XCTest
@testable import MaterialSnake


class PositionTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }
    
    func testIsAproxEqual() {
        for _ in 1...100 {
            let position = Position(x: drand48(), y: drand48())
            let test = Position(x: drand48(), y: drand48())
            XCTAssert(position.isApproxEqual(test))
            let x = Double(arc4random()) + drand48()
            let y = Double(arc4random()) + drand48()
            let other = Position(x: x, y: y)
            if x - y > 1.0 || y - x < -1.0 {
                XCTAssert(!other.isApproxEqual(test))
            }
        }
    }
}
