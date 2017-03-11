
import Foundation


class Map: SpaceEntity {

    /// The origin point of the map (x, y) [Top Left Corner]
    var position: Position
    
    /// The size of the map (width, height)
    var size: Size
    
    /// The exact size of the cell (width, height)
    var cell: Size
    
    /// The number of columns in our map.
    lazy var cols: Double = self.size.width / self.cell.width
    
    /// The number of rows in our map.
    lazy var rows: Double = self.size.height / self.cell.height
    
    /**
     The map needs a cell in order to divide by itself into rows and columns.
     The CellConfig object gives your map a starting value 
                                (an approximation for the cell size)
     ````
     var approximateWidth: Int = the approximate width of the cell (by default = 20)
     var approximateHeight: Int = the approximate height of the cell (by default = 20)
     ````
     */
    struct CellConfig {
        var approximateWidth: Int = 20
        var approximateHeight: Int = 20
    }

    /**
     The map needs a cell in order to divide by itself into rows and columns.

     - parameter position: the origin position of the map
     - parameter size: the size of the map
     - parameter cell: the approximate size of the cell
     */
    init(position: Position, size: Size, cell: CellConfig) {
        self.position = position
        self.size = size
        self.cell = Size()
        self.cell = { [unowned me = self] () -> Size in
            let cols = Int(ceil(me.size.width - me.position.x)) / cell.approximateWidth
            let rows = Int(ceil(me.size.height - me.position.y)) / cell.approximateHeight
            return Size(width: (me.size.width - me.position.x) / Double(cols),
                        height: (me.size.height - me.position.y) / Double(rows))
        }()
    }

    /// - returns: new random position on the map [no restrictions]
    func getRandomPosition() -> Position {
        let xPosition = position.x + Double(arc4random_uniform(UInt32(cols))) * cell.width
        let yPosition = position.y + Double(arc4random_uniform(UInt32(rows))) * cell.height
        return Position(x: xPosition, y: yPosition)
    }

    /**
     Checks if a position is on the map.
     - parameter position: the position we need to test
     */
    func isInside(position: Position) -> Bool {
        return !isOutside(position: position)
    }

    /**
     Checks if a position is not on the map.
     - parameter position: the position we need to test
     */
    func isOutside(position: Position) -> Bool {
        return self.position.x > position.x ||
                self.position.y > position.y ||
                self.position.x + self.size.width <= position.x ||
                self.position.y + self.size.height <= position.y
    }
    
}
