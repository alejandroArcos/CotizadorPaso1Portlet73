package com.tokio.pa.paso1portlet73.portlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.tokio.pa.cotizadorModularServices.Bean.ListaRegistro;
import com.tokio.pa.cotizadorModularServices.Bean.Registro;
import com.tokio.pa.cotizadorModularServices.Constants.CotizadorModularServiceKey;
import com.tokio.pa.cotizadorModularServices.Interface.CotizadorPaso1;
import com.tokio.pa.paso1portlet73.constants.CotizadorPaso1Portlet73PortletKeys;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + CotizadorPaso1Portlet73PortletKeys.COTIZADORPASO1PORTLET73,
				"mvc.command.name=/cotizadores/paso1/gradoRiesgo" }, service = MVCResourceCommand.class)

public class gradoRiesgoResourceCommand extends BaseMVCResourceCommand {


	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		/************************** Validación metodo post **************************/
		if ( !resourceRequest.getMethod().equals("POST")  ){
			JsonObject requestError = new JsonObject();
			requestError.addProperty("code", 500);
			requestError.addProperty("msg", "Error en tipo de consulta");
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(requestError.toString());
			return;
		}
		/************************** Validación metodo post **************************/
		
		System.out.println("entre al gradoRiesgo");
		long idSubGiro = ParamUtil.getLong(resourceRequest, "subGiro");
		
		JsonObject evento = new JsonObject();

		evento.addProperty("incendio", "prueba 1");
		evento.addProperty("rc", "prueba 2");
		evento.addProperty("productos", "prueba 3");
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(evento);
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(jsonString);

	}
}
