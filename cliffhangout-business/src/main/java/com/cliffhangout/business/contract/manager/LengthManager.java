package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Length;

public interface LengthManager {
    void deleteLength(Length length);
    void updateLength(Length length);
    void createLength(Length length);
}
