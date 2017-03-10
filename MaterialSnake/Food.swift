
import Foundation


enum FoodType {

    /// Normal Food Value
    case Normal

    /// Includes Bonus Value
    case Bonus

}

struct Food: SpaceEntity {

    /// The food's position on the map
    var position: Position

    /// The size of the food ** Recommended ** Use map's exact cell size
    var size: Size

    /// The food's type [Normal/Bonus]
    private (set) var type: FoodType

    /// Default Food Value
    var initValue: Int = 20

    /// The Value's Factor
    /// - note: **Only for food with type FoodType.Bonus**
    var factor: Int = 5

    /// The Food's Value [Score]
    var value: Int {
        switch type {
            case.Normal: return initValue
            case.Bonus: return factor * initValue
        }
    }

    /**
     - parameter position: The food's position on the map
     - parameter size: The size of the food ** Recommended ** Use map's exact cell size
     - parameter type: The food's type

     ## Possible Values For Food Type ##
     ````
     * value .Normal : Food with normal value [normal amount of points for score]
     * value .Bonus : Food with bonus value [normal value * factor] where factor is adjustable.
     ````
    */
    init(position: Position, size: Size, type: FoodType) {
        self.position = position
        self.size = size
        self.type = type
    }

}
