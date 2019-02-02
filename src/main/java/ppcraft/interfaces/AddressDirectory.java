package ppcraft.interfaces;

import ppcraft.objects.AddressStatusPing;

public interface AddressDirectory {
    void add(AddressStatusPing addressStatusPing);

    void update(AddressStatusPing addressStatusPing);

    void delete(AddressStatusPing addressStatusPing);
}
