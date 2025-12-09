package compact.fp.io

import compact.fp.model._

object Parser {

  def parseComponentType(s: String): ComponentType = s.trim.toLowerCase match {
    case "cpu" => CPU
    case "gpu" => GPU
    case "ram" => RAM
    case "motherboard" => Motherboard
    case "storage" => Storage
    case _ => throw new IllegalArgumentException(s"Unknown type: $s")
  }

  def parse(line: String): Option[Component] = {
    val parts = line.split(",").map(_.trim).toList
    parts match {
      case id :: name :: manuf :: tpeStr :: compatStr :: _ =>
        val compatibleIds = compatStr.split("[|\\s]+").map(_.trim).filter(_.nonEmpty).toList
        try {
          Some(Component(
            id,
            name,
            manuf,
            parseComponentType(tpeStr),
            compatibleIds
          ))
        } catch { case _: IllegalArgumentException => None }
      case _ => None
    }
  }
}
