
import Foundation
import UIKit


struct Position {
    
    var x: Double
    var y: Double
    
    init(x: Int, y: Int) {
        self.x = Double(x)
        self.y = Double(y)
    }
    
    init(x: CGFloat, y: CGFloat) {
        self.x = Double(x)
        self.y = Double(y)
    }
    
    init(x: Double, y: Double) {
        self.x = x
        self.y = y
    }
    
    init() {
        self.x = 0.0
        self.y = 0.0
    }
    
    /**
     Tests if two objects have approximately the same position.

     - note: tolerance = 1.0
     - parameter other: the object's position
     - returns Bool
     */
    func isApproxEqual(_ other: Position) -> Bool {
        return (ceil(x) == ceil(other.x)) &&
            (ceil(y) == ceil(other.y))
    }
    
    static func ==(left: Position, right: Position) -> Bool {
        return left.x == right.x && left.y == right.y
    }
    
}
