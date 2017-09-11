package com.jodelapp.utilities;


import com.jodelapp.utilities.rx.ThreadTransformer;

public interface UtilsComponent {

    StringUtils exposeStringUtils();

    ThreadTransformer exposeThreadTransformer();

}
