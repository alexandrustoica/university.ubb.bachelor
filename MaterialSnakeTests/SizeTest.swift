
import XCTest
@testable import MaterialSnake

class SizeTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
    }
    
    override func tearDown() {
        super.tearDown()
    }
    
    func testIsAproxEqual() {
        for _ in 1...100 {
            let sizeA = Size(width: drand48(), height: drand48())
            let sizeB = Size(width: drand48(), height: drand48())
            XCTAssert(sizeA.isApproxEqual(sizeB))
            let width = Double(arc4random()) + drand48()
            let height = Double(arc4random()) + drand48()
            let sizeC = Size(width: width, height: height)
            if width - height > 1.0 || width - height < -1.0 {
                XCTAssert(!sizeA.isApproxEqual(sizeC))
            }
        }
    }
        
}
