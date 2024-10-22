package spendreport.detailed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * A detailed alert with additional information of the zip code
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class DetailedAlert {

    private long id;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DetailedAlert alert = (DetailedAlert) o;
            return this.id == alert.id;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "DetailedAlert{" +
                "id=" + id +
                '}';
    }
}
