package compact.fp.model

sealed trait ComponentType

case object CPU extends ComponentType
case object GPU extends ComponentType
case object RAM extends ComponentType
case object Motherboard extends ComponentType
case object Storage extends ComponentType

final case class Component(
                            id: String,
                            name: String,
                            manufacturer: String,
                            compType: ComponentType,
                            compatibleIds: List[String]
                          )


