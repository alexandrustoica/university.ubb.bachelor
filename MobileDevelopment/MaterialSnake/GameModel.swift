
import Foundation


class GameModel: Observable {

    /// The current round in a level. [five rounds = one level]
    private var round: Int = 1

    private var food: Food
    private weak var map: Map?
    private weak var snake: Snake?
    private (set) var game: Game
    
    /// The snake's current position on the map. [returns the whole body]
    var positionSnake: [Position] {
        get {
            if snake != nil {
                return snake!.body
            }
            return []
        }
    }

    /// The food's current position on the map. [returns the whole body]
    private (set) var positionFood: [Position] {
        get {
            return food.body
        }
        set {
            self.positionFood = food.body
            notifyObservers(with: Event(havingType: .FoodChange))
        }
    }

    /// The snake's current speed.
    private (set) var speed: Double {
        didSet {
            notifyObservers(with: Event(havingType: .SpeedChange))
        }
    }

    /// The game's current status.
    private (set) var status: GameStatus {
        didSet {
            notifyObservers(with: Event(havingType: .StatusChange))
        }
    }

    /// The game's current level.
    private (set) var level: Int {
        get {
            return game.level
        }
        set {
            game.level = newValue
            notifyObservers(with: Event(havingType: .LevelChange))
        }
    }

    /// The game's current score.
    private (set) var score: Int {
        get {
            return game.score
        }
        set {
            game.score = newValue
            notifyObservers(with: Event(havingType: .ScoreChange))
        }
    }

    override init() {
        self.food = Food(position: Position(), size: Size(), type: .Normal)
        self.game = Game()
        self.speed = 0.25
        self.status = .Running
        super.init()
    }

    /// - parameter map: The game's map [playable area on the screen]
    convenience init(map: Map) {
        self.init()
        self.map = map
        self.snake = Snake(position: map.getRandomPosition(), size: map.cell)
        self.food = Food(position: map.getRandomPosition(),
                         size: map.cell,
                         type: .Normal)
        self.speed = 0.25
    }

    deinit {
        map = nil
        snake = nil
    }

    /// Describes the context of the snake's current position.
    enum SnakePositionProperty {

        /// The snake is outside of the map's area.
        case Outside

        /// The snake is eating the food.
        case EatingFood

        /// The snake is eating itself.
        case EatingSnake

    }

    /**
     Checks the snake's current position and returns a list of position properties.
     - see also: SnakePositionProperty Documentation
    */
    func checkSnakePosition() -> [SnakePositionProperty]? {
        var accumulator = [SnakePositionProperty]()
        if map == nil || snake == nil {
            return nil
        }
        if map!.isOutside(position: snake!.position) {
            accumulator.append(.Outside)
        }
        if food.isEaten(by: snake!.position) {
            accumulator.append(.EatingFood)
        }
        if snake!.isSelfEating() {
            accumulator.append(.EatingSnake)
        }
        return accumulator
    }

    /**
     Effect: Ends the game if the game's type is "Classic".
                [because the snake is outside the borders of the map]

     Alternative: Shifts the snakes position and rechecks the new position if the game's type is "Free".
    */
    private func performingOperationOutsideSnake() {
        switch game.type {
            case .Classic:
                status = .Terminated
            case .Free:
                shiftSnake()
                let recheckProperties = checkSnakePosition()
                update(knowing: recheckProperties)
        }
    }

    /**
     Calculates the current score, level, round and speed of the game.
     Update the food with a new value [because the snake has eaten the current food object]
    */
    private func performingOperationEatingFood() {
        if snake == nil {
            return
        }
        score += level * food.value
        updateFood()
        snake!.incrementTail()
        round += 1
        if round % 5 == 0 {
            level += 1
            round = 1
            speed >= 0.15 ? speed -= 0.05 : ()
        }
    }

    /// Ends the game because the snake is eating itself.
    private func performingOperationEatingSnake() {
        status = .Terminated
    }

    /// Performs an operation based on the value of the position property.
    private func performOperation(knowing property: SnakePositionProperty) {
        switch property {
            case .EatingFood:
                performingOperationEatingFood()
            case .Outside:
                performingOperationOutsideSnake()
            case .EatingSnake:
                performingOperationEatingSnake()
        }
    }

    /// Updates the system knowing the list of position properties. [snake's position]
    func update(knowing properties: [SnakePositionProperty]?) {
        if properties == nil {
            return
        }
        for property in properties! {
            performOperation(knowing: property)
        }
    }

    /// Moves the snake with one row/column on the map.
    func moveSnake() {
        if snake != nil {
            snake!.move()
        }
    }

    /// Updates the snake's direction
    /// - note: [if the new direction is not the opposite of the old one]
    func updateSnakeDirection(with direction: Direction) {
        if snake == nil || snake!.direction == direction {
            return
        }
        if !Direction.isPossible(old: snake!.direction.type, new: direction.type) {
            return
        }
        snake!.direction = direction
    }

    /// Shifts the snake's current position to the opposite side of the map.
    private func shiftSnake() {
        if snake == nil || map == nil {
            return
        }
        if snake!.position.x < map!.position.x {
            snake!.position = Position(x: snake!.position.x + map!.size.width,
                                       y: snake!.position.y)
        } else if snake!.position.x >= map!.position.x + map!.size.width {
            snake!.position = Position(x: snake!.position.x - map!.size.width,
                                       y: snake!.position.y)
        } else if snake!.position.y < map!.position.y {
            snake!.position = Position(x: snake!.position.x,
                                       y: snake!.position.y + map!.size.height)
        } else if snake!.position.y >= map!.position.y + map!.size.height {
            snake!.position = Position(x: snake!.position.x,
                                       y: snake!.position.y - map!.size.height)
        }
    }

    /// Updates the food based on the current position of the snake.
    private func updateFood() {
        if map == nil || snake == nil {
            return
        }
        var position = map!.getRandomPosition()
        repeat {
            position = map!.getRandomPosition()
        } while snake!.isOnSnake(position: position)
        if round == 5 {
            food = Food(position: position, size: map!.cell, type: .Bonus)
        } else {
            food = Food(position: position, size: map!.cell, type: .Normal)
        }
    }

}
