package infrastructure;

import java.util.List;
import java.util.Optional;

public interface EntityMapper<T, J> {
    Optional<T> toEntity(J data);

    List<T> toEntityList(J data);
}
