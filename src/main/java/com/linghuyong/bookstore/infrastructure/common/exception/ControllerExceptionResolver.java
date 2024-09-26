package com.linghuyong.bookstore.infrastructure.common.exception;

import com.google.gson.Gson;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;


@Service
public class ControllerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setStatus(200);
        Gson gson = new Gson();
        Response<Void> apiResponse;
        if (ex instanceof ServerRuntimeException serverRuntimeException) {
            apiResponse = Response.failed(serverRuntimeException.getErrorCode(), serverRuntimeException.getMessage());
        } else {
            apiResponse = Response.failed(ErrorCode.Unknown, ex.getMessage());
        }

        try {
            response.getWriter().write(gson.toJson(apiResponse));
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
