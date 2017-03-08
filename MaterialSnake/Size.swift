
import Foundation


struct Size {
    
    var width: Double
    var height: Double
    
    init(width: Int, height: Int) {
        self.width = Double(width)
        self.height = Double(height)
    }
    
    init(width: Double, height: Double) {
        self.width = width
        self.height = height
    }
    
    init() {
        self.width = 0.0
        self.height = 0.0
    }
    
    /**
     Tests if two objects have approximately the same size.
     - note: tolerance = 1.0
     - parameter other: the object's size
     - returns Bool
     */
    public func isAproxEqual(_ other: Size) -> Bool {
        return ceil(width) == ceil(other.width) &&
            ceil(height) == ceil(other.height)
    }
    
    static func ==(left: Size, right: Size) -> Bool {
        return left.width == right.width && left.height == right.height
    }
    
}
