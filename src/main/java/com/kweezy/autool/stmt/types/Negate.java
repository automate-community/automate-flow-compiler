package com.kweezy.autool.stmt.types;

import com.kweezy.autool.stmt.types.fieldcount.OneField;

public final class Negate extends OneField
{
    // public Double b(final y0 y0) { // TODO: Add y0 class
    //     return -Formatting.H0(super.first.k1(y0));
    // }

    @Override
    public String prettyPrint(final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-");
        sb.append(super.first.prettyPrint(n));
        return sb.toString();
    }
}
