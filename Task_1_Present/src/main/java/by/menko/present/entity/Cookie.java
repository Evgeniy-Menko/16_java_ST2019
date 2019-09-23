package by.menko.present.entity;


import java.util.Objects;

/**
 * @author Evgeniy Menko
 */
public class Cookie extends Sweets {
    /**
     * type cookie.
     */
    static final String TYPE = "cookie";
    /**
     * cookie's view.
     */
    private String view;

    /**
     * Constructor chocolate.
     *
     * @param name   name.
     * @param weight weight.
     * @param sugar  sugar.
     * @param cost   cost.
     * @param views  view.
     */
    public Cookie(final String name, final int weight,
                  final int sugar, final int cost, final String views) {
        super(TYPE, name, weight, sugar, cost);
        this.view = views;
    }

    /**
     * Getter.
     *
     * @return view.
     */
    public String getView() {
        return view;
    }

    /**
     * Setter.
     *
     * @param views view.
     */
    public void setView(final String views) {
        this.view = views;
    }

    /**
     * Equals.
     *
     * @param o Object.
     *
     * @return true or false.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Cookie cookie = (Cookie) o;
        return Objects.equals(view, cookie.view);
    }

    /**
     * HashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), view);
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + "," + view + "\n";
    }

    /**
     * Print Object.
     *
     * @return String.
     */
    @Override
    public String print() {
        return super.print() + ", view= " + view;
    }
}

