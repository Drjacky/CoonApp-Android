package com.jodelapp.data;


import com.jodelapp.data.api.ApiService;

public interface DataComponent {
    ApiService exposeApiService();
}
