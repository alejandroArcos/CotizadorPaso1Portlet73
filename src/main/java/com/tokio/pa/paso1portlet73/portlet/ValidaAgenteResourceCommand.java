package com.tokio.pa.paso1portlet73.portlet;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.pa.cotizadorModularServices.Bean.SimpleResponse;
import com.tokio.pa.cotizadorModularServices.Interface.CotizadorGenerico;
import com.tokio.pa.paso1portlet73.constants.CotizadorPaso1Portlet73PortletKeys;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CotizadorPaso1Portlet73PortletKeys.COTIZADORPASO1PORTLET73,
		"mvc.command.name=/cotizadores/validaAgente"
	},
	service = MVCResourceCommand.class
)

public class ValidaAgenteResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	CotizadorGenerico _CMGenerico;
	
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		String auxCodigo = ParamUtil.getString(resourceRequest, "codigoAgente");
		int cotizacion = ParamUtil.getInteger(resourceRequest, "cotizacion");
		String codigoAgente = "";
		
		if(!auxCodigo.isEmpty()) {
			String auxCodAgente[] = auxCodigo.split("-");
			codigoAgente = auxCodAgente[0].trim();
		}
		
		SimpleResponse response = _CMGenerico.validaAgentes(cotizacion, codigoAgente,
				CotizadorPaso1Portlet73PortletKeys.PANTALLA_EMPRESARIAL);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(jsonString);
		
	}

}
