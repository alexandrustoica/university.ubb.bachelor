
import XCTest
@testable import MaterialSnake


class MapTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }

    func testGetRandomPosition() {
        let outside = Map(position: Position(x: 0, y: 0), size: Size(width: 200, height: 200), cell: Map.CellConfig())
        let inside = Map(position: Position(x: 50, y: 50), size: Size(width: 100, height: 100), cell: Map.CellConfig())
        for _ in 0...100 {
            let point = inside.getRandomPosition()
            XCTAssertTrue(outside.isInside(position: point))
            XCTAssertTrue(inside.isInside(position: point))
            XCTAssertFalse(inside.isOutside(position: point))
            let position = outside.getRandomPosition()
            if !inside.isInside(position: position) {
                XCTAssertTrue(outside.isInside(position: position))
                XCTAssertFalse(outside.isOutside(position: position))
            }
        }
    }

}
