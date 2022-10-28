/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya;

import burp.api.montoya.burpsuite.BurpSuite;
import burp.api.montoya.collaborator.Collaborator;
import burp.api.montoya.collaborator.CollaboratorClient;
import burp.api.montoya.collaborator.CollaboratorPayload;
import burp.api.montoya.collaborator.CollaboratorServer;
import burp.api.montoya.collaborator.DnsQueryType;
import burp.api.montoya.collaborator.Interaction;
import burp.api.montoya.collaborator.InteractionFilter;
import burp.api.montoya.collaborator.InteractionId;
import burp.api.montoya.collaborator.InteractionType;
import burp.api.montoya.collaborator.PayloadOption;
import burp.api.montoya.collaborator.SecretKey;
import burp.api.montoya.collaborator.SmtpProtocol;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.core.Range;
import burp.api.montoya.core.Registration;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.core.Version;
import burp.api.montoya.extension.Extension;
import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.http.ContentType;
import burp.api.montoya.http.Http;
import burp.api.montoya.http.HttpHandler;
import burp.api.montoya.http.HttpProtocol;
import burp.api.montoya.http.HttpService;
import burp.api.montoya.http.HttpTransformation;
import burp.api.montoya.http.RequestResult;
import burp.api.montoya.http.ResponseResult;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.MarkedHttpRequestResponse;
import burp.api.montoya.http.message.cookies.Cookie;
import burp.api.montoya.http.message.headers.HttpHeader;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.http.message.responses.analysis.AttributeType;
import burp.api.montoya.http.message.responses.analysis.KeywordCount;
import burp.api.montoya.http.message.responses.analysis.ResponseKeywordsAnalyzer;
import burp.api.montoya.http.message.responses.analysis.ResponseVariationsAnalyzer;
import burp.api.montoya.http.sessions.CookieJar;
import burp.api.montoya.http.sessions.SessionHandlingAction;
import burp.api.montoya.intruder.AttackConfiguration;
import burp.api.montoya.intruder.HttpRequestTemplate;
import burp.api.montoya.intruder.Intruder;
import burp.api.montoya.intruder.IntruderInsertionPoint;
import burp.api.montoya.intruder.Payload;
import burp.api.montoya.intruder.PayloadGenerator;
import burp.api.montoya.intruder.PayloadGeneratorProvider;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.intruder.PayloadProcessor;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.persistence.Persistence;
import burp.api.montoya.persistence.Preferences;
import burp.api.montoya.proxy.InterceptedHttpRequest;
import burp.api.montoya.proxy.InterceptedHttpResponse;
import burp.api.montoya.proxy.Proxy;
import burp.api.montoya.proxy.ProxyHttpRequestHandler;
import burp.api.montoya.proxy.ProxyHttpResponseHandler;
import burp.api.montoya.proxy.ProxyRequestResponse;
import burp.api.montoya.proxy.RequestFinalInterceptResult;
import burp.api.montoya.proxy.RequestInitialInterceptResult;
import burp.api.montoya.proxy.ResponseFinalInterceptResult;
import burp.api.montoya.proxy.ResponseInitialInterceptResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.Crawl;
import burp.api.montoya.scanner.InvalidLauncherConfigurationException;
import burp.api.montoya.scanner.Scan;
import burp.api.montoya.scanner.ScanCheck;
import burp.api.montoya.scanner.Scanner;
import burp.api.montoya.scanner.audit.Audit;
import burp.api.montoya.scanner.audit.AuditIssueHandler;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPointProvider;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPointType;
import burp.api.montoya.scanner.audit.insertionpoint.ExtensionGeneratedAuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueDefinition;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import burp.api.montoya.scope.Scope;
import burp.api.montoya.scope.ScopeChange;
import burp.api.montoya.scope.ScopeChangeHandler;
import burp.api.montoya.sitemap.SiteMap;
import burp.api.montoya.sitemap.SiteMapFilter;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import burp.api.montoya.ui.editor.RawEditor;
import burp.api.montoya.ui.editor.extension.EditorMode;
import burp.api.montoya.ui.editor.extension.ExtensionHttpRequestEditor;
import burp.api.montoya.ui.editor.extension.ExtensionHttpRequestEditorProvider;
import burp.api.montoya.ui.editor.extension.ExtensionHttpResponseEditor;
import burp.api.montoya.ui.editor.extension.ExtensionHttpResponseEditorProvider;
import burp.api.montoya.utilities.Utilities;
import burp.api.montoya.websocket.Direction;
import burp.api.montoya.websocket.WebSocket;
import burp.api.montoya.websocket.WebSocketBinaryMessage;
import burp.api.montoya.websocket.WebSocketCreationHandler;
import burp.api.montoya.websocket.WebSocketHandler;
import burp.api.montoya.websocket.WebSocketTextMessage;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static burp.api.montoya.core.Annotations.annotations;
import static burp.api.montoya.core.ByteArray.byteArray;
import static burp.api.montoya.core.Range.range;
import static burp.api.montoya.http.HttpMode.HTTP_1;
import static burp.api.montoya.http.HttpMode.HTTP_2;
import static burp.api.montoya.http.HttpMode.HTTP_2_IGNORE_ALPN;
import static burp.api.montoya.http.HttpService.httpService;
import static burp.api.montoya.http.RequestResult.requestResult;
import static burp.api.montoya.http.ResponseResult.responseResult;
import static burp.api.montoya.http.message.MarkedHttpRequestResponse.markedRequestResponse;
import static burp.api.montoya.http.message.headers.HttpHeader.httpHeader;
import static burp.api.montoya.http.message.params.HttpParameter.bodyParameter;
import static burp.api.montoya.http.message.params.HttpParameter.cookieParameter;
import static burp.api.montoya.http.message.params.HttpParameter.urlParameter;
import static burp.api.montoya.http.message.requests.HttpRequest.httpRequest;
import static burp.api.montoya.http.message.requests.HttpRequest.httpRequestFromUrl;
import static burp.api.montoya.http.message.requests.HttpRequest.httpVerbatimRequest;
import static burp.api.montoya.http.message.responses.HttpResponse.httpResponse;
import static burp.api.montoya.scanner.BuiltInScanConfiguration.ACTIVE_AUDIT_CHECKS;
import static burp.api.montoya.scanner.BuiltInScanConfiguration.PASSIVE_AUDIT_CHECKS;
import static burp.api.montoya.scanner.ReportFormat.XML;
import static burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint.auditInsertionPoint;
import static burp.api.montoya.scanner.audit.issues.AuditIssue.auditIssue;
import static burp.api.montoya.ui.Selection.selection;
import static burp.api.montoya.websocket.WebSocketBinaryMessage.continueWithBinaryMessage;
import static burp.api.montoya.websocket.WebSocketTextMessage.continueWithTextMessage;
import static burp.api.montoya.websocket.WebSocketTextMessage.dropTextMessage;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;

@SuppressWarnings("all")
public class TestExtension implements BurpExtension
{
    private MontoyaApi api;
    private Http http;
    private Logging logging;
    private SiteMap siteMap;
    private Scanner scanner;
    private UserInterface userInterface;
    private Collaborator collaborator;
    private BurpSuite burpSuite;
    private Extension extension;
    private Scope scope;
    private Proxy proxy;
    private Intruder intruder;
    private Persistence persistence;
    private Utilities utilities;

    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private HttpRequestResponse httpRequestResponse;

    @Override
    public void initialize(MontoyaApi api)
    {
        this.api = api;
        http = api.http();
        logging = api.logging();
        siteMap = api.siteMap();
        scanner = api.scanner();
        extension = api.extension();
        userInterface = api.userInterface();
        collaborator = api.collaborator();
        burpSuite = api.burpSuite();
        scope = api.scope();
        proxy = api.proxy();
        intruder = api.intruder();
        persistence = api.persistence();
        utilities = api.utilities();

        httpRequest = httpRequest(httpService("example.org", true), "GET / HTTP/1.1\r\nHost: example.org\r\n\r\n");
        httpResponse = httpResponse("HTTP/1.1 200 OK \r\n\r\n");
        httpRequestResponse = HttpRequestResponse.httpRequestResponse(httpRequest, httpResponse);

        logging.logToOutput("Hello World!");
    }

    private void addScanIssue()
    {
        siteMap.add(auditIssue(
                "My Issue",
                "Details",
                "Remediation detail",
                httpRequest.url(),
                AuditIssueSeverity.INFORMATION,
                AuditIssueConfidence.FIRM,
                "background of issue",
                "remediation of issue",
                AuditIssueSeverity.LOW,
                List.of(
                        markedRequestResponse(httpRequest, httpResponse),
                        markedRequestResponse(httpRequest, httpResponse, annotations(HighlightColor.BLUE)),
                        markedRequestResponse(httpRequest, httpResponse, Annotations.annotations("comment")),
                        markedRequestResponse(httpRequest, httpResponse, Annotations.annotations("comment", HighlightColor.GREEN))
                )
        ));
    }

    private void addSuiteTab()
    {
        userInterface.registerSuiteTab("Krusty Krab", new JPanel());
    }

    private void addToSiteMap()
    {
        HttpRequestResponse requestResponse = markedRequestResponse(httpRequest, httpResponse, Annotations.annotations("test comment", HighlightColor.BLUE));

        siteMap.add(requestResponse);
    }

    private void applyMarkers()
    {
        List<Range> requestMarkers = List.of(range(0, 10), range(23, 51));
        List<Range> responseMarkers = List.of(range(5, 7), range(16, 17));

        MarkedHttpRequestResponse markedRequestResponse1 = httpRequestResponse.withMarkers(requestMarkers, responseMarkers);

        MarkedHttpRequestResponse markedRequestResponse2 = httpRequestResponse.withRequestMarkers(requestMarkers);
        MarkedHttpRequestResponse markedRequestResponse3 = httpRequestResponse.withRequestMarkers(range(0, 10), range(23, 51));

        MarkedHttpRequestResponse markedRequestResponse4 = httpRequestResponse.withResponseMarkers(responseMarkers);
        MarkedHttpRequestResponse markedRequestResponse5 = httpRequestResponse.withResponseMarkers(range(5, 7), range(16, 17));

        System.out.println(markedRequestResponse1.requestMarkers());
        System.out.println(markedRequestResponse1.responseMarkers());
    }

    private void createBurpCollaboratorClientContext()
    {
        CollaboratorClient collaboratorClient = collaborator.createClient();

        SecretKey secretKey = collaboratorClient.getSecretKey();

        for (Interaction interaction : collaboratorClient.getAllInteractions())
        {
            InteractionId id = interaction.id();
            InteractionType type = interaction.type();
            InetAddress ip = interaction.clientIp();
            ZonedDateTime zonedDateTime = interaction.timeStamp();
            String customData = interaction.customData().get();

            ByteArray query = interaction.dnsDetails().get().query();
            DnsQueryType dnsQueryType = interaction.dnsDetails().get().queryType();
            HttpProtocol httpProtocol = interaction.httpDetails().get().protocol();
            HttpRequestResponse requestResponse = interaction.httpDetails().get().requestResponse();
            SmtpProtocol smtpProtocol = interaction.smtpDetails().get().protocol();
            String conversation = interaction.smtpDetails().get().conversation();
        }

        List<Interaction> filteredInteractions = collaboratorClient.getInteractions(InteractionFilter.interactionPayloadFilter("payload"));

        CollaboratorPayload payload = collaboratorClient.generatePayload();
        String payloadString = payload.toString();
        String payloadId = payload.id().toString();
        CollaboratorServer payloadServer = payload.server().get();

        CollaboratorPayload payloadWithCustomData = collaboratorClient.generatePayload("custom");
        String customData = payloadWithCustomData.customData().get();

        CollaboratorPayload payloadWithoutServerLocation = collaboratorClient.generatePayload(PayloadOption.WITHOUT_SERVER_LOCATION);

        String address = collaboratorClient.server().address();
        boolean isLiteralAddress = collaboratorClient.server().isLiteralAddress();
    }

    private void createMessageEditor()
    {
        HttpRequestEditor httpRequestEditor = userInterface.createHttpRequestEditor();

        JComponent jComponent = httpRequestEditor.uiComponent();
        int requestCaretPosition = httpRequestEditor.caretPosition();
        ByteArray requestSelectionContents = httpRequestEditor.selection().get().contents();
        Range requestSelectionOffsets = httpRequestEditor.selection().get().offsets();
        HttpRequest request = httpRequestEditor.getRequest();
        boolean isRequestEditorModified = httpRequestEditor.isModified();
        httpRequestEditor.setRequest(httpRequest);
        httpRequestEditor.setSearchExpression("Foo");

        HttpResponseEditor httpResponseEditor = userInterface.createHttpResponseEditor();
        int responseCaretPosition = httpResponseEditor.caretPosition();
        ByteArray responseSelectionContents = httpResponseEditor.selection().get().contents();
        Range responseSelectionOffsets = httpResponseEditor.selection().get().offsets();
        HttpResponse response = httpResponseEditor.getResponse();
        boolean isResponseEditorModified = httpResponseEditor.isModified();
        httpResponseEditor.setResponse(httpResponse);
        httpResponseEditor.setSearchExpression("Bar");
    }

    private void createTextEditor()
    {
        RawEditor rawEditor = userInterface.createRawEditor();

        rawEditor.setContents(byteArray("Hello World!"));
        ByteArray contents = rawEditor.getContents();
    }

    private void customizeUiComponent()
    {
        JLabel label = new JLabel("This is an example.");
        JPanel panel = new JPanel();
        panel.add(label);

        userInterface.applyThemeToComponent(panel);
    }

    private void doActiveScan() throws InvalidLauncherConfigurationException
    {
        Scan scan = scanner.createScan();

        scan.addRequest(httpRequest);
        scan.addConfiguration(ACTIVE_AUDIT_CHECKS);

        Audit audit = scan.startAudit();

        audit.delete();

        List<AuditIssue> issues = audit.issues();
        for (AuditIssue issue : issues)
        {
            System.out.println(issue.name());
            System.out.println(issue.baseUrl());
            System.out.println(issue.detail());
            System.out.println(issue.severity());
            System.out.println(issue.confidence());
            System.out.println(issue.requestResponses());
            System.out.println(issue.httpService());

            AuditIssueDefinition definition = issue.definition();
            System.out.println(definition.name());
            System.out.println(definition.background());
            System.out.println(definition.typeIndex());
            System.out.println(definition.remediation());
            System.out.println(definition.typicalSeverity());
        }

        int errors = audit.errorCount();
        int insertionPointCount = audit.insertionPointCount();
        int requestCount = audit.requestCount();
        String statusMessage = audit.statusMessage();
    }

    private void doActiveScanWithInsertionPoints() throws InvalidLauncherConfigurationException
    {
        Scan scan = scanner.createScan();

        Utilities utilities = this.utilities;
        Range range1 = range(0, 10);
        Range range2 = range(23, 51);

        scan.addRequest(httpRequest, List.of(range1, range2));
        scan.addConfiguration(ACTIVE_AUDIT_CHECKS);

        Audit audit = scan.startAudit();
    }

    private void doPassiveScan() throws InvalidLauncherConfigurationException
    {
        Scan scan = scanner.createScan();

        scan.addRequestResponse(httpRequestResponse);
        scan.addConfiguration(PASSIVE_AUDIT_CHECKS);

        Audit audit = scan.startAudit();
    }

    private void excludeFromScope() throws Exception
    {
        scope.excludeFromScope("https://example.com:442/");
    }

    private void exitSuite()
    {
        burpSuite.shutdown();
    }

    private void generateScanReport()
    {
        List<AuditIssue> issues = siteMap.issues(SiteMapFilter.prefixFilter("https://example.org"));

        scanner.generateReport(issues, XML, Path.of("/usr/report/issues.xml"));
    }

    private void getBurpVersion()
    {
        Version version = burpSuite.version();

        System.out.println(version.toString());
        System.out.println(version.name());
        System.out.println(version.major());
        System.out.println(version.minor());
        System.out.println(version.build());
    }

    private void getCommandLineArguments()
    {
        List<String> arguments = burpSuite.commandLineArguments();
    }

    private void getCookieJarContents()
    {
        CookieJar cookieJar = http.cookieJar();

        for (Cookie cookie : cookieJar.cookies())
        {
            System.out.println(cookie.name());
            System.out.println(cookie.domain());
            System.out.println(cookie.path());
            System.out.println(cookie.expiration());
            System.out.println(cookie.value());
        }
    }

    private void getExtensionFilename()
    {
        String filename = extension.filename();
    }


    private void getProxyHistory()
    {
        for (ProxyRequestResponse requestResponse : proxy.history())
        {
            System.out.println(requestResponse.finalRequest());
            System.out.println(requestResponse.originalResponse());
            System.out.println(requestResponse.messageAnnotations().comment());
            System.out.println(requestResponse.messageAnnotations().highlightColor());
        }
    }

    private void getScanIssues()
    {
        List<AuditIssue> issues = siteMap.issues(SiteMapFilter.prefixFilter("https://example.org"));
    }

    private void getSiteMap()
    {
        List<HttpRequestResponse> httpRequestResponses = siteMap.requestResponses(SiteMapFilter.prefixFilter("https://example.org"));
    }

    private void getStderr()
    {
        PrintStream err = logging.error();
        err.println("Error");
    }

    private void getStdout()
    {
        PrintStream out = logging.output();
        out.println("Out");
    }

    private void getToolName()
    {
        System.out.println(ToolType.INTRUDER.toolName());
    }

    private void includeInScope() throws Exception
    {
        scope.includeInScope("https://example.com:442/");
    }

    private void isExtensionBapp()
    {
        boolean isBapp = extension.isBapp();
    }

    private void isInScope() throws Exception
    {
        boolean inScope = scope.isInScope("https://example.com:442/");
    }

    private void issueAlert()
    {
        logging.raiseDebugEvent("I can hear howling");
        logging.raiseInfoEvent("Sean might have been raised by wolves");
        logging.raiseErrorEvent("Sean was raised by wolves");
        logging.raiseCriticalEvent("Sean is literally a wolf");
    }

    private void loadConfigFromJson()
    {
        burpSuite.importProjectOptionsFromJson("{\"a\" : 6}");
    }

    private void loadExtensionSetting()
    {
        Preferences preferenceFileContext = persistence.preferences();
        System.out.println(preferenceFileContext.getString("stringName"));
        System.out.println(preferenceFileContext.getBoolean("booleanName"));
        System.out.println(preferenceFileContext.getInteger("numberName"));
    }

    private void makeHttp2Request()
    {
        HttpRequestResponse requestResponse = http.issueRequest(httpRequest, HTTP_2);
    }

    private void makeHttp2RequestForced()
    {
        HttpRequestResponse requestResponse = http.issueRequest(httpRequest, HTTP_2_IGNORE_ALPN);
    }

    private void makeHttp2RequestWithConnectionIdentifier()
    {
        HttpRequestResponse requestResponse = http.issueRequest(httpRequest, HTTP_2, "pool-1");
    }

    private void makeHttpRequest()
    {
        HttpRequestResponse requestResponse = http.issueRequest(httpRequest);
    }

    private void makeHttp1RequestForced()
    {
        http.issueRequest(httpRequest, HTTP_1);
    }

    private void printError()
    {
        logging.logToError("Error");
    }

    private void printOutput()
    {
        logging.logToOutput("Output");
    }

    private void registerContextMenuFactory()
    {
        Registration contextMenuItemsProvider = userInterface.registerContextMenuItemsProvider(event -> {
            if (event.isFromTool(ToolType.INTRUDER) && event.messageEditorRequestResponse().isPresent())
            {
                return List.of(new JMenuItem("foo"), new JMenuItem("bar"));
            }
            else
            {
                if (!event.selectedRequestResponses().isEmpty())
                {
                    return List.of(new JMenuItem("barfoo"));
                }
                else
                {
                    return emptyList();
                }
            }
        });

        if (contextMenuItemsProvider.isRegistered())
        {
            contextMenuItemsProvider.deregister();
        }
    }

    private void registerExtensionStateListener()
    {
        Registration registration = extension.registerUnloadingHandler(new ExtensionUnloadingHandler()
        {
            @Override
            public void extensionUnloaded()
            {
                //Clean up resources.
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerHttpListener()
    {
        http.registerHttpHandler(new HttpHandler()
        {
            @Override
            public RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolSource)
            {
                List<HttpParameter> parameters = List.of(urlParameter("foo", "bar"), bodyParameter("foo2", "bar2"));

                HttpRequest modifiedRequest = httpRequest.withAddedParameters(parameters);

                return requestResult(modifiedRequest, annotations.withComment("new comment"));
            }

            @Override
            public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest initiatingRequest, Annotations annotations, ToolSource toolSource)
            {
                return responseResult(response, annotations);
            }
        });
    }

    private void registerIntruderPayloadGeneratorFactory()
    {
        Registration registration = intruder.registerPayloadGeneratorProvider(new PayloadGeneratorProvider()
        {
            @Override
            public String displayName()
            {
                return "Too late for yoga";
            }

            @Override
            public PayloadGenerator providePayloadGenerator(AttackConfiguration attackConfiguration)
            {
                return new PayloadGenerator()
                {
                    private int currentRequestCounter;

                    @Override
                    public Payload generatePayloadFor(IntruderInsertionPoint insertionPoint)
                    {
                        if (currentRequestCounter >= 100)
                        {
                            return Payload.END;
                        }

                        boolean isEven = currentRequestCounter % 2 == 0;

                        return Payload.from(isEven ? "Even-" + currentRequestCounter : "Odd-" + currentRequestCounter);
                    }
                };
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerIntruderPayloadProcessor()
    {
        Registration registration = intruder.registerPayloadProcessor(new PayloadProcessor()
        {
            @Override
            public String displayName()
            {
                return "Si";
            }

            @Override
            public PayloadProcessingResult processPayload(
                    Payload currentPayload,
                    Payload originalPayload,
                    IntruderInsertionPoint insertionPoint)
            {
                ByteArray value = currentPayload.value();
                value.setByte(2, (byte) 0x01);

                return PayloadProcessingResult.usePayload(value);
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerMessageEditorTabFactory()
    {
        ExtensionHttpRequestEditor requestEditor = new ExtensionHttpRequestEditor()
        {
            private HttpRequest httpRequest;

            @Override
            public HttpRequest getHttpRequest()
            {
                return httpRequest;
            }

            @Override
            public void setHttpRequestResponse(HttpRequestResponse requestResponse)
            {
                this.httpRequest = requestResponse.httpRequest();
            }

            @Override
            public boolean isEnabledFor(HttpRequestResponse requestResponse)
            {
                return requestResponse.httpRequest().method().equalsIgnoreCase("POST") && requestResponse.httpRequest().httpService() != null;
            }

            @Override
            public String caption()
            {
                return "Post only editor";
            }

            @Override
            public Component uiComponent()
            {
                return new JPanel();
            }

            @Override
            public Selection selectedData()
            {
                return selection(1, 5);
            }

            @Override
            public boolean isModified()
            {
                return false;
            }
        };

        Registration requestEditorRegistration = userInterface.registerHttpRequestEditorProvider(new ExtensionHttpRequestEditorProvider()
        {
            @Override
            public ExtensionHttpRequestEditor provideHttpRequestEditor(HttpRequestResponse requestResponse, EditorMode editorMode)
            {
                if (requestResponse.httpRequest().method().equals("GET") && editorMode == EditorMode.READ_ONLY)
                {
                    return requestEditor;
                }
                else
                {
                    return null;
                }
            }
        });

        if (requestEditorRegistration.isRegistered())
        {
            requestEditorRegistration.deregister();
        }

        ExtensionHttpResponseEditor genericResponseEditor = new ExtensionHttpResponseEditor()
        {
            private HttpResponse httpResponse;

            @Override
            public HttpResponse getHttpResponse()
            {
                return httpResponse;
            }

            @Override
            public void setHttpRequestResponse(HttpRequestResponse requestResponse)
            {
                this.httpResponse = requestResponse.httpResponse();
            }

            @Override
            public boolean isEnabledFor(HttpRequestResponse requestResponse)
            {
                return requestResponse.httpRequest().method().equalsIgnoreCase("POST") && requestResponse.httpResponse().statusCode() == 404;
            }

            @Override
            public String caption()
            {
                return null;
            }

            @Override
            public Component uiComponent()
            {
                return null;
            }

            @Override
            public Selection selectedData()
            {
                return null;
            }

            @Override
            public boolean isModified()
            {
                return false;
            }
        };

        Registration responseEditorRegistration = userInterface.registerHttpResponseEditorProvider(new ExtensionHttpResponseEditorProvider()
        {
            @Override
            public ExtensionHttpResponseEditor provideHttpResponseEditor(HttpRequestResponse httpRequestResponse, EditorMode editorMode)
            {
                if (editorMode != EditorMode.READ_ONLY)
                {
                    return genericResponseEditor;
                }
                else
                {
                    return null;
                }
            }
        });

        if (responseEditorRegistration.isRegistered())
        {
            responseEditorRegistration.deregister();
        }
    }

    private void registerProxyListener()
    {
        proxy.registerRequestHandler(new ProxyHttpRequestHandler()
        {
            @Override
            public RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest, Annotations annotations)
            {
                String comment = annotations.comment();

                if (comment == null)
                {
                    comment = "Foo";
                }

                HighlightColor highlight = annotations.highlightColor();

                if (highlight == null)
                {
                    highlight = HighlightColor.BLUE;
                }

                HttpRequest modifiedRequest = interceptedRequest.withAddedParameters(bodyParameter("foo", "bar"));

                Annotations updatedAnnotations = Annotations.annotations(comment, highlight);

                return RequestInitialInterceptResult.followUserRules(modifiedRequest, updatedAnnotations);
            }

            @Override
            public RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest, Annotations modifiableAnnotations)
            {
                String listenerInterface = interceptedRequest.listenerInterface();
                int messageId = interceptedRequest.messageId();
                InetAddress sourceIpAddress = interceptedRequest.sourceIpAddress();
                InetAddress targetIpAddress = interceptedRequest.destinationIpAddress();

                return RequestFinalInterceptResult.continueWith(interceptedRequest);
            }
        });

        Registration registration = proxy.registerResponseHandler(new ProxyHttpResponseHandler()
        {
            @Override
            public ResponseInitialInterceptResult handleReceivedResponse(
                    InterceptedHttpResponse interceptedResponse,
                    HttpRequest httpRequest,
                    Annotations modifiableAnnotations)
            {
                return ResponseInitialInterceptResult.followUserRules(interceptedResponse);
            }

            @Override
            public ResponseFinalInterceptResult handleResponseToReturn(
                    InterceptedHttpResponse interceptedResponse,
                    HttpRequest httpRequest,
                    Annotations modifiableAnnotations)
            {
                return ResponseFinalInterceptResult.drop();
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerScannerCheck()
    {
        Registration registration = scanner.registerScanCheck(new ScanCheck()
        {
            @Override
            public List<AuditIssue> activeAudit(HttpRequestResponse baseRequestResponse, AuditInsertionPoint auditInsertionPoint)
            {
                if (auditInsertionPoint.type() == AuditInsertionPointType.HEADER)
                {
                    return emptyList();
                }

                AuditIssue issue = auditIssue(
                        "My Issue",
                        "Details",
                        "Remediation detail",
                        baseRequestResponse.httpRequest().url(),
                        AuditIssueSeverity.INFORMATION,
                        AuditIssueConfidence.FIRM,
                        "background of issue",
                        "remediation of issue",
                        AuditIssueSeverity.LOW,
                        List.of(baseRequestResponse.withNoMarkers())
                );

                return List.of(issue);
            }

            @Override
            public List<AuditIssue> passiveAudit(HttpRequestResponse baseRequestResponse)
            {
                AuditIssue issue = auditIssue(
                        "My Issue",
                        "Details",
                        "Remediation detail",
                        baseRequestResponse.httpRequest().url(),
                        AuditIssueSeverity.HIGH,
                        AuditIssueConfidence.CERTAIN,
                        "this is going to cost a lot of money",
                        "remediation of issue",
                        AuditIssueSeverity.LOW,
                        baseRequestResponse.withNoMarkers()
                );

                return List.of(issue);
            }

            @Override
            public ConsolidationAction consolidateIssues(AuditIssue newIssue, AuditIssue existingIssue)
            {
                return newIssue == existingIssue
                        ? ConsolidationAction.KEEP_NEW
                        : ConsolidationAction.KEEP_BOTH;
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerScannerInsertionPointProvider()
    {
        Registration registration = scanner.registerInsertionPointProvider(new AuditInsertionPointProvider()
        {
            @Override
            public List<ExtensionGeneratedAuditInsertionPoint> provideInsertionPoints(HttpRequestResponse baseRequestResponse)
            {
                ExtensionGeneratedAuditInsertionPoint firstTenCharactersInsertionPoint = new ExtensionGeneratedAuditInsertionPoint()
                {
                    private final Range range = range(0, 10);

                    @Override
                    public String name()
                    {
                        return "Definitely an insertion point";
                    }

                    @Override
                    public String baseValue()
                    {
                        ByteArray requestBytes = baseRequestResponse.httpRequest().asBytes();
                        String requestString = requestBytes.toString();

                        return requestString.substring(range.startIndexInclusive(), range.endIndexExclusive());
                    }

                    @Override
                    public ByteArray buildHttpMessageWithPayload(ByteArray payload)
                    {
                        ByteArray requestBytes = baseRequestResponse.httpRequest().asBytes();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        int payloadLength = payload.length();

                        for (int i = 0; i < requestBytes.length(); i++)
                        {
                            if (i >= range.startIndexInclusive())
                            {
                                outputStream.write(payload.getBytes(), 0, payloadLength);
                                i += payloadLength;
                            }
                            else
                            {
                                outputStream.write(requestBytes.getByte(i));
                            }
                        }

                        return ByteArray.byteArray(outputStream.toByteArray());
                    }

                    @Override
                    public List<Range> issueHighlights(ByteArray payload)
                    {
                        return List.of(range);
                    }
                };

                return List.of(firstTenCharactersInsertionPoint);
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerScannerListener()
    {
        scanner.registerAuditIssueHandler(new AuditIssueHandler()
        {
            @Override
            public void handleNewAuditIssue(AuditIssue auditIssue)
            {
                if (auditIssue.severity() == AuditIssueSeverity.HIGH)
                {
                    // Play level up music.
                }
            }
        });
    }

    private void registerScopeChangeListener()
    {
        Registration registration = scope.registerHandler(new ScopeChangeHandler()
        {
            @Override
            public void scopeChanged(ScopeChange scopeChange)
            {
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void registerSessionHandlingAction()
    {
        Registration registration = http.registerSessionHandlingAction(new SessionHandlingAction()
        {
            @Override
            public String name()
            {
                return "action name";
            }

            @Override
            public RequestResult handle(HttpRequest currentRequest, Annotations messageAnnotations, List<HttpRequestResponse> macroRequestResponses)
            {
                HttpRequest updatedRequest = currentRequest.withRemovedParameters(bodyParameter("foo", "bar"));
                Annotations updatedAnnotations = messageAnnotations.withComment("updated");

                return requestResult(updatedRequest, updatedAnnotations);
            }
        });

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void removeHttpListener()
    {
        HttpHandler handler = new HttpHandler()
        {
            @Override
            public RequestResult handleHttpRequest(HttpRequest request, Annotations annotations, ToolSource toolContext)
            {
                return requestResult(httpRequest, annotations);
            }

            @Override
            public ResponseResult handleHttpResponse(HttpResponse response, HttpRequest initiatingRequest, Annotations annotations, ToolSource toolSource)
            {
                return responseResult(response, annotations);
            }
        };

        Registration registration = http.registerHttpHandler(handler);

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void removeScannerListener()
    {
        AuditIssueHandler handler = auditIssue -> {
        };

        Registration registration = scanner.registerAuditIssueHandler(handler);

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void removeProxyListener()
    {
        ProxyHttpRequestHandler handler = new ProxyHttpRequestHandler()
        {
            @Override
            public RequestInitialInterceptResult handleReceivedRequest(InterceptedHttpRequest interceptedRequest, Annotations modifiableAnnotations)
            {
                return RequestInitialInterceptResult.drop();
            }

            @Override
            public RequestFinalInterceptResult handleRequestToIssue(InterceptedHttpRequest interceptedRequest, Annotations modifiableAnnotations)
            {
                return RequestFinalInterceptResult.drop();
            }
        };

        Registration registration = proxy.registerRequestHandler(handler);

        if (registration.isRegistered())
        {
            registration.deregister();
        }
    }

    private void saveBuffersToTempFiles()
    {
        persistence.temporaryFile().setHttpRequestResponse("foo", httpRequestResponse);
        HttpRequestResponse persistedHttpRequestResponse = persistence.temporaryFile().getHttpRequestResponse("foo");

        persistence.temporaryFile().setHttpRequest("bar", httpRequest);
        HttpRequest persistedHttpRequest = persistence.temporaryFile().getHttpRequest("bar");

        persistence.temporaryFile().setHttpResponse("baz", httpResponse);
        HttpResponse persistedHttpResponse = persistence.temporaryFile().getHttpResponse("baz");
    }

    private void saveConfigAsJson()
    {
        String fancyJson = burpSuite.exportProjectOptionsAsJson("some.json.path");
    }

    private void saveExtensionSetting()
    {
        Preferences preferences = persistence.preferences();
        preferences.setBoolean("booleanValue", true);
        preferences.setByte("byteValue", (byte) 0x0A);
        preferences.setShort("shortValue", (short) 1);
        preferences.setInteger("intValue", 123);
        preferences.setLong("longValue", 123456);
        preferences.setString("stringValue", "bar");

        preferences.delete("intValue");
    }

    private void sendToComparer()
    {
        api.comparer().sendToComparer(ByteArray.byteArray());
    }

    private void sendToIntruder()
    {
        intruder.sendToIntruder(httpRequest);
    }

    private void sendToIntruderWithOffsets()
    {
        List<Range> payloadPostionOffsets = List.of(range(5, 6));
        HttpRequestTemplate requestTemplate = HttpRequestTemplate.httpRequestTemplate(httpRequest, payloadPostionOffsets);
        intruder.sendToIntruder(httpService("example.org", true), requestTemplate);
    }

    private void sendToRepeater()
    {
        api.repeater().sendToRepeater("random tab name", httpRequest);

        api.repeater().sendToRepeater(httpRequest);
    }

    private void sendToSpider() throws InvalidLauncherConfigurationException
    {
        Scan scan = scanner.createScan();

        scan.addUrl("http://example.org/login");

        Crawl crawl = scan.startCrawl();
    }

    private void setProxyInterceptionEnabled()
    {
        proxy.enableIntercept();
        proxy.disableIntercept();
    }

    private void unloadExtension()
    {
        extension.unload();
    }

    private void updateCookieJar()
    {
        http.cookieJar().setCookie("foo", "bar", "/", "example.org", ZonedDateTime.now());
    }

    //IExtensionHelpers
    private void addParameter()
    {
        HttpRequest modifiedRequest = httpRequest.withAddedParameters(urlParameter("foo", "bar"));
    }

    private void analyseRequest()
    {
        HttpRequest request = httpRequest(httpService("example.org", true), "GET / HTTP/1.0\r\n\r\n");

        int i = request.bodyOffset();
        ByteArray body = request.body();
        ContentType contentType = request.contentType();
        List<HttpHeader> headers = request.headers();
        String method = request.method();
        List<ParsedHttpParameter> parameters = request.parameters();
        String url = request.url();
    }

    private void analyseResponse()
    {
        HttpResponse response = httpResponse(byteArray("HTTP/1.1 200 OK\r\n\r\n"));

        int i = response.bodyOffset();
        ByteArray body = response.body();
        List<Cookie> cookies = response.cookies();
        List<HttpHeader> headers = response.headers();
        String mimeTypeDescription = response.inferredMimeType().description();
        String statedMimeType = response.statedMimeType().description();
        short statusCode = response.statusCode();
    }

    private void analyzeResponseKeywords()
    {
        ResponseKeywordsAnalyzer analyzer = http.createResponseKeywordsAnalyzer(List.of("key", "word"));

        analyzer.updateWith(httpResponse);
        Set<String> invariantKeywords = analyzer.invariantKeywords();
        Set<String> variantKeywords = analyzer.variantKeywords();
        List<KeywordCount> count = httpResponse.keywordCounts("foo");
    }

    private void analyzeResponseVariations()
    {
        ResponseVariationsAnalyzer analyzer = http.createResponseVariationsAnalyzer();

        analyzer.updateWith(httpResponse);
        Set<AttributeType> invariantAttributes = analyzer.invariantAttributes();
        Set<AttributeType> variantAttributes = analyzer.variantAttributes();
        List<KeywordCount> count = httpResponse.keywordCounts("foo");
    }

    private void base64Decode()
    {
        utilities.base64Utils().getDecoder().decode("base64data");
        utilities.base64Utils().getDecoder().decode("base64data".getBytes(UTF_8));
    }

    private void base64Encode()
    {
        utilities.base64Utils().getEncoder().encode("base64Data".getBytes(UTF_8));
    }

    private void buildHeader()
    {
        HttpHeader header = httpHeader("foo", "bar");
        String name = header.name();
        String value = header.value();
    }

    private void buildHttpMessage()
    {
        List<String> headers = List.of("foo: bar");

        HttpRequest request = httpRequest(null, headers, ByteArray.byteArray());

        HttpResponse response = httpResponse(headers, ByteArray.byteArray());
    }

    private void buildHttpRequest() throws MalformedURLException
    {
        HttpRequest requestFromUrl = httpRequestFromUrl("https://example.com:442/");
        HttpRequest requestFromBytes = httpRequest(httpService("example.org", true), byteArray("GET / HTTP/1.0\r\n\r\n"));
        HttpRequest requestFromString = httpRequest(httpService("example.org", true), "GET / HTTP/1.0\r\n\r\n");
        HttpRequest requestFromHeadersAndBody = httpRequest(httpService("example.org", true), List.of(new String("foo: bar")), ByteArray.byteArray());
        HttpRequest exactRequestFromHeadersAndBody = httpVerbatimRequest(httpService("example.org", true), List.of(httpHeader("foo: bar")), ByteArray.byteArray());
    }

    private void buildHttpService()
    {
        HttpService service = httpService("example.com", 8080, false);
        HttpService service1 = httpService("example.com", false);

        String host = service.host();
        int port = service.port();
        boolean secure = service.secure();
    }

    private void buildParameter()
    {
        HttpParameter urlParam = urlParameter("foo", "bar");
        HttpParameter bodyParam = bodyParameter("foo", "bar");
        HttpParameter cookie = cookieParameter("foo", "bar");
    }

    private void bytesToString()
    {
        System.out.println(utilities.byteUtils().convertToString(new byte[0]));
    }

    private void getRequestParameter()
    {
        List<ParsedHttpParameter> parameters = httpRequest.parameters();
        ParsedHttpParameter param = parameters.stream().filter(p -> p.name().equals("foo")).findFirst().get();

        String name = param.name();
        HttpParameterType type = param.type();
        String value = param.value();
        Range nameOffsets = param.nameOffsets();
        Range valueOffsets = param.valueOffsets();
    }

    private void indexOf()
    {
        utilities.byteUtils().indexOf(new byte[0], new byte[0], true, 0, 1);
    }

    private void makeScannerInsertionPoint() throws IOException
    {
        AuditInsertionPoint typedAuditInsertionPoint = auditInsertionPoint("foo", httpRequest, 0, 5);

        String insertionPointName = typedAuditInsertionPoint.name();
        AuditInsertionPointType insertionPointType = typedAuditInsertionPoint.type();
        String baseValue = typedAuditInsertionPoint.baseValue();

        ByteArray request = typedAuditInsertionPoint.buildHttpMessageWithPayload(byteArray("Foo"));
        List<Range> highlightOffsets = typedAuditInsertionPoint.issueHighlights(byteArray("Foo"));
    }

    private void removeParameter()
    {
        HttpRequest modifiedRequest = httpRequest.withRemovedParameters(urlParameter("foo", "bar"));
    }

    private void stringToBytes()
    {
        byte[] converted = utilities.byteUtils().convertFromString("foo");
    }

    private void toggleRequestMethod()
    {
        HttpRequest modifiedRequest = httpRequest.withTransformationApplied(HttpTransformation.TOGGLE_METHOD);
    }

    private void updateParameter()
    {
        HttpRequest modifiedRequest = httpRequest.withUpdatedParameters(urlParameter("foo", "bar"));
    }

    private void urlDecode()
    {
        System.out.println(utilities.urlUtils().decode("foo"));
    }

    private void urlEncode()
    {
        System.out.println(utilities.urlUtils().encode("foo"));
    }

    private void webSocketExample() {
        api.websockets().registerWebSocketCreationHandler(new WebSocketCreationHandler()
        {
            @Override
            public void handleWebSocketCreated(WebSocket webSocket, HttpRequest upgradeRequest, ToolSource toolSource)
            {
                if (upgradeRequest.url().contains("foo.bar")) {
                    webSocket.registerHandler(new WebSocketHandler()
                    {
                        @Override
                        public WebSocketTextMessage handleTextMessage(String textMessage, Direction direction)
                        {
                            if (textMessage.contains("zap"))
                            {
                                return dropTextMessage();
                            }

                            String payload = direction == Direction.CLIENT_TO_SERVER ? textMessage + "foo" : textMessage + "bar";

                            return continueWithTextMessage(payload);
                        }

                        @Override
                        public WebSocketBinaryMessage handleBinaryMessage(ByteArray binaryMessage, Direction direction)
                        {
                            return continueWithBinaryMessage(binaryMessage);
                        }
                    });
                }
            }
        });
    }
}