# Spring AI Tools

A RESTful API that acts as an intelligent conversational agent. The system processes user prompts using Google Gemini via Spring AI, maintains session-based memory, and autonomously fetches real-time news data when required.

## Technology Stack

- **Language:** Java 25
- **Build Tool:** Gradle 8.14.5 (Groovy DSL)
- **Framework:** Spring Boot 4.0.7
- **AI Framework:** Spring AI 2.0.0
- **LLM:** Google Gemini (gemini-2.5-flash)

## Features

- **Chat endpoint** — `GET /api/chat?prompt=...&conversationId=...`
- **Conversational memory** — session-based context retention isolated by `conversationId`
- **Tool calling** — LLM autonomously invokes a news-fetching tool when the user asks about current events
- **Synchronous HTTP** — uses Spring's `RestClient` for all outbound calls

## Getting Started

### Prerequisites

- Java 25
- A [Google Gemini API key](https://aistudio.google.com/)
- A [NewsAPI key](https://newsapi.org/)

### Configuration

Set the following environment variables:

```bash
export GOOGLE_GEMINI_API_KEY=your-gemini-api-key
export NEWS_API_KEY=your-newsapi-key
```

### Build & Run

```bash
./gradlew bootRun
```

### Usage

```bash
# Simple question (no tool invocation)
curl "http://localhost:8080/api/chat?prompt=What+is+2%2B2"

# News query (triggers news tool)
curl "http://localhost:8080/api/chat?prompt=latest+news+about+SpaceX&conversationId=session-1"

# Follow-up in same session (uses memory)
curl "http://localhost:8080/api/chat?prompt=tell+me+more+about+the+first+article&conversationId=session-1"
```

## Architecture

```
┌────────────┐     ┌────────────────┐     ┌──────────────────┐
│  HTTP GET  │────▶│ ChatController │────▶│    ChatClient     │
│  /api/chat │     │                │     │  + Memory Advisor │
└────────────┘     └────────────────┘     └────────┬─────────┘
                                                   │
                                          ┌────────▼─────────┐
                                          │  Google Gemini    │
                                          │  (gemini-2.5-flash)│
                                          └────────┬─────────┘
                                                   │ (tool call)
                                          ┌────────▼─────────┐
                                          │   NewsTools       │
                                          │  (newsapi.org)    │
                                          └──────────────────┘
```
